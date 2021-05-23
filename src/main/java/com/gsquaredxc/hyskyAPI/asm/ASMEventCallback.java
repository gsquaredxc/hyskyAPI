package com.gsquaredxc.hyskyAPI.asm;

import com.google.common.collect.Maps;
import com.gsquaredxc.hyskyAPI.eventListeners.Event;

import java.lang.reflect.Method;
import java.util.HashMap;


public class ASMEventCallback
{
    private static int IDs = 0;
    /*private static final String HANDLER_DESC = Type.getInternalName(IEventCallback.class);
    private static final String HANDLER_FUNC_DESC = Type.getMethodDescriptor(Type.getType(void.class),Type.getType(Event.class));*/
    private static final ASMClassLoader LOADER = new ASMClassLoader();
    private static final HashMap<Method, Class<?>> cache = Maps.newHashMap();

    private final IEventCallback handler;
    private final String readable;

    public ASMEventCallback(final Method method) throws Exception
    {
        handler = (IEventCallback)createWrapper(method).getConstructor().newInstance();
        readable = "ASM: " + method.getName();
    }

    public void invoke(final Event event)
    {
        handler.invoke(event);
    }

    public Class<?> createWrapper(final Method callback)
    {
        if (cache.containsKey(callback))
        {
            return cache.get(callback);
        }

        final String name = getUniqueName(callback);
        final String desc = name.replace('.',  '/');
        final String instType = callback.getDeclaringClass().getName().replace('.',  '/');
        final String eventType = callback.getParameterTypes()[0].getName().replace('.',  '/');
        final Class<?> ret = LOADER.define(name, ASMClassGenerator.generateNormalSnippetB(desc,instType, callback.getName(), eventType));
        cache.put(callback, ret);
        return ret;
    }

    private String getUniqueName(final Method callback)
    {
        return String.format("%s_%d_%s_%s_%s", getClass().getName(), IDs++,
                callback.getDeclaringClass().getSimpleName(),
                callback.getName(),
                callback.getParameterTypes()[0]);
    }

    private static class ASMClassLoader extends ClassLoader
    {
        private ASMClassLoader()
        {
            super(ASMClassLoader.class.getClassLoader());
        }

        public Class<?> define(final String name, final byte[] data)
        {
            return defineClass(name, data, 0, data.length);
        }
    }

    public String toString()
    {
        return readable;
    }
}

/*
        final ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        cw.visit(V1_6, ACC_PUBLIC | ACC_SUPER, desc, null, "java/lang/Object", new String[]{ HANDLER_DESC });

        cw.visitSource(".dynamic", null);
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "invoke", HANDLER_FUNC_DESC, null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, eventType);
            mv.visitMethodInsn(INVOKESTATIC, instType, callback.getName(), Type.getMethodDescriptor(callback), false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 2);
            mv.visitEnd();
        }
        cw.visitEnd();
*/
