package com.gsquaredxc.hyskyAPI.asm;

public class ASMClassGenerator {
    /*static final Byte[] cafebabe = {-54, -2, -70, -66};
    static final Byte[] v50 = {0, 0, 0, 50};*/


    final static byte[] fullstart_a = {-54, -2, -70, -66, 0, 0, 0, 50, 0, 23};
    final static byte[] fullstart_b = {-54, -2, -70, -66, 0, 0, 0, 50, 0, 24};

    final static byte[] instr215 = {7, 0, 1, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 7, 0, 3, 1, 0, 42, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 97, 115, 109, 47, 73, 69, 118, 101, 110, 116, 67, 97, 108, 108, 98, 97, 99, 107, 7, 0, 5, 1, 0, 8, 46, 100, 121, 110, 97, 109, 105, 99, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 8, 0, 9, 10, 0, 4, 0, 10, 1, 0, 6, 105, 110, 118, 111, 107, 101, 1, 0, 49, 40, 76, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 59, 41, 86, 1, 0, 44, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 7, 0, 14};
    final static byte[] instr17 = {7, 0, 16};
    final static byte[] instr1922 = {12, 0, 18, 0, 13, 10, 0, 17, 0, 19 , 1, 0, 4, 67, 111, 100, 101, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101};

    final static byte[] instr213 = {7, 0, 1, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 7, 0, 3, 1, 0, 42, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 97, 115, 109, 47, 73, 69, 118, 101, 110, 116, 67, 97, 108, 108, 98, 97, 99, 107, 7, 0, 5, 1, 0, 8, 46, 100, 121, 110, 97, 109, 105, 99, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 8, 0, 9, 10, 0, 4, 0, 10, 1, 0, 6, 105, 110, 118, 111, 107, 101, 1, 0, 49, 40, 76, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 59, 41, 86};
    final static byte[] instr15 = {7, 0, 14};
    final static byte[] instr2023 = {12, 0, 18, 0, 19, 10, 0, 17, 0, 20, 1, 0, 4, 67, 111, 100, 101, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101};
    //code differences:
    //register numbers on code_bytes assume that there is 1 less than in other_code
    final static byte[] code_bytes_a = {0, 33, 0, 2, 0, 4, 0, 1, 0, 6, 0, 0, 0, 2, 0, 1, 0, 8, 0, 9, 0, 1, 0, 21, 0, 0, 0, 17, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 11, -79, 0, 0, 0, 0, 0, 1, 0, 12, 0, 13, 0, 1, 0, 21, 0, 0, 0, 20, 0, 1, 0, 2, 0, 0, 0, 8, 43, -64, 0, 15, -72, 0, 20, -79, 0, 0, 0, 0, 0, 1, 0, 22, 0, 0, 0, 2, 0, 7};
    final static byte[] code_bytes_b = {0, 33, 0, 2, 0, 4, 0, 1, 0, 6, 0, 0, 0, 2, 0, 1, 0, 8, 0, 9, 0, 1, 0, 22, 0, 0, 0, 17, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 11, -79, 0, 0, 0, 0, 0, 1, 0, 12, 0, 13, 0, 1, 0, 22, 0, 0, 0, 20, 0, 1, 0, 2, 0, 0, 0, 8, 43, -64, 0, 15, -72, 0, 21, -79, 0, 0, 0, 0, 0, 1, 0, 23, 0, 0, 0, 2, 0, 7};
    //                                                                                                      ^^                                                                                                     ^^                                                               ^^                            ^^

    /*ArrayList<Byte> byteArrayList = new ArrayList<>();
    int numconst = 1;
    public void addStartSnippet(){
        byteArrayList.addAll(Arrays.asList(cafebabe));
        byteArrayList.addAll(Arrays.asList(v50));
    }*/
    /*
    CONSTANT_Utf8_info {
        u1 tag;
        u2 length;
        u1 bytes[length];
    }
     */



    public static byte[] generateNormalSnippetA(final String name, final String clazz, final String method){
        //this.addStartSnippet();
        //this.addLength(23);
        //[0,23]
        //1
        //this.addFromUTF8("com/gsquaredxc/hyskyAPI/asm/ASMEventCallback_0_HyskyAPI_test"); //mutable
        final byte[] instr1 = genFromUTF8(name);
        //2
        //this.addFromClass();
        //[7, 0, 1]
        //3
        //this.addFromUTF8("java/lang/Object"); //immutable
        //[1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116]
        //4
        //this.addFromClass();
        //[7, 0, 3]
        //5
        //this.addFromUTF8("com/gsquaredxc/hyskyAPI/asm/IEventCallback"); //immutable
        //[1, 0, 42, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 97, 115, 109, 47, 73, 69, 118, 101, 110, 116, 67, 97, 108, 108, 98, 97, 99, 107]
        //6
        //this.addFromClass();
        //[7, 0, 5]
        //7
        //this.addFromUTF8(".dynamic"); //immutable
        //[1, 0, 8, 46, 100, 121, 110, 97, 109, 105, 99]
        //8
        //this.addFromUTF8("<init>"); //immutable
        //[1, 0, 6, 60, 105, 110, 105, 116, 62]
        //9
        //this.addFromUTF8("()V"); //immutable
        //[1, 0, 3, 40, 41, 86]
        //10 0x0A
        //this.addFromNT();
        //[12, 0, 8, 0, 9]
        //11 0x0B
        //this.addFromMethod(4,10);
        //[10, 0, 4, 0, 10]
        //12
        //this.addFromUTF8("invoke"); //immutable
        //{1, 0, 6, 105, 110, 118, 111, 107, 101}
        //13
        //this.addFromUTF8("(Lcom/gsquaredxc/hyskyAPI/eventListeners/Event;)V"); //immutable
        //{1, 0, 49, 40, 76, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 59, 41, 86}
        //14
        //this.addFromUTF8("com/gsquaredxc/hyskyAPI/eventListeners/Event"); //immutable (apparently wrong)
        //{1, 0, 44, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116}
        //15
        //this.addFromClass();
        //[7, 0, 14]
        //16
        //this.addFromUTF8("com/gsquaredxc/hyskyAPI/HyskyAPI"); //mutable
        final byte[] instr16 = genFromUTF8(clazz);
        //17
        //this.addFromClass();
        //[7, 0, 16]
        //18
        //this.addFromUTF8("test"); //mutable
        final byte[] instr18 = genFromUTF8(method);
        /*19
        //this.addFromNT(18,13);
        //[12, 0, 18, 0, 13]
        //20
        //this.addFromMethod(17,19);
        //[10, 0, 17, 0, 19]
        //21
        //this.addFromUTF8("Code"); //immutable
        //22
        //this.addFromUTF8("SourceFile"); //immutable*/
        //[1, 0, 4, 67, 111, 100, 101]
        final byte[] output = new byte[fullstart_a.length+instr1.length+instr215.length+instr16.length+instr17.length+instr18.length+instr1922.length+code_bytes_a.length];
        int pointer = 0;
        pointer += addToArray(fullstart_a,output,pointer);
        pointer += addToArray(instr1,output,pointer);
        pointer += addToArray(instr215,output,pointer);
        pointer += addToArray(instr16,output,pointer);
        pointer += addToArray(instr17,output,pointer);
        pointer += addToArray(instr18,output,pointer);
        pointer += addToArray(instr1922,output,pointer);
        addToArray(code_bytes_a,output,pointer);
        return output;

    }

    public static byte[] generateNormalSnippetB(final String name, final String clazz, final String method, final String event){
        final byte[] instr1 = genFromUTF8(name);
        final byte[] instr14 = genFromUTF8(event);
        final byte[] instr16 = genFromUTF8(clazz);
        final byte[] instr18 = genFromUTF8(method);
        final byte[] instr19 = genFromUTF8("(L"+event+";)V");
        final byte[] output = new byte[fullstart_b.length+instr1.length+instr213.length+instr14.length+instr15.length+instr16.length+instr17.length+instr18.length+instr19.length+instr2023.length+code_bytes_b.length];
        int pointer = 0;
        pointer += addToArray(fullstart_b,output,pointer);
        pointer += addToArray(instr1,output,pointer);
        pointer += addToArray(instr213,output,pointer);
        pointer += addToArray(instr14,output,pointer);
        pointer += addToArray(instr15,output,pointer);
        pointer += addToArray(instr16,output,pointer);
        pointer += addToArray(instr17,output,pointer);
        pointer += addToArray(instr18,output,pointer);
        pointer += addToArray(instr19,output,pointer);
        pointer += addToArray(instr2023,output,pointer);
        addToArray(code_bytes_b,output,pointer);
        return output;

    }

    public static int addToArray(final byte[] byteToAdd, final byte[] output, final int pointer){
        final int length = byteToAdd.length;
        System.arraycopy(byteToAdd,0,output,pointer,length);
        return length;
    }

    /*public void addLength(final int i){
        byteArrayList.add((byte) (i << 8));
        byteArrayList.add((byte) i);
    }

    public void addFromUTF8(final String s){
        final byte[] stringbytes = s.getBytes();
        final byte first = (byte) stringbytes.length;
        final byte second = (byte) (stringbytes.length << 8);
        byteArrayList.add((byte)1);
        byteArrayList.add(second);
        byteArrayList.add(first);
        for(final Byte b: stringbytes) {
            byteArrayList.add(b);
        }
        numconst++;
    }*/

    public static byte[] genFromUTF8(final String s){
        final byte[] stringbytes = s.getBytes();
        final byte[] shortList = {(byte)1,(byte)(stringbytes.length << 8),(byte)stringbytes.length};
        final byte[] output = new byte[stringbytes.length+3];
        System.arraycopy(shortList,0,output,0,3);
        System.arraycopy(stringbytes,0,output,3,stringbytes.length);
        return output;
    }

    /*public void addFromClass(){
        addFromClass(numconst-1);
    }

    public void addFromClass(final int index){
        byteArrayList.add((byte)7);
        byteArrayList.add((byte) (index << 8));
        byteArrayList.add((byte) index);
        numconst++;
    }

    public void addFromNT(){
        addFromNT(numconst-2,numconst-1);
    }

    public void addFromNT(final int name,final int discr){
        byteArrayList.add((byte)12);
        byteArrayList.add((byte) (name << 8));
        byteArrayList.add((byte) name);
        byteArrayList.add((byte) (discr << 8));
        byteArrayList.add((byte) discr);
        numconst++;
    }

    public void addFromMethod(final int clazz,final int nt){
        byteArrayList.add((byte)10);
        byteArrayList.add((byte) (clazz << 8));
        byteArrayList.add((byte) clazz);
        byteArrayList.add((byte) (nt << 8));
        byteArrayList.add((byte) nt);
        numconst++;
    }*/
    //byte[] b = {0, 23, 1, 0, 60, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 97, 115, 109, 47, 65, 83, 77, 69, 118, 101, 110, 116, 67, 97, 108, 108, 98, 97, 99, 107, 95, 48, 95, 72, 121, 115, 107, 121, 65, 80, 73, 95, 116, 101, 115, 116, 7, 0, 1, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 7, 0, 3, 1, 0, 42, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 97, 115, 109, 47, 73, 69, 118, 101, 110, 116, 67, 97, 108, 108, 98, 97, 99, 107, 7, 0, 5, 1, 0, 8, 46, 100, 121, 110, 97, 109, 105, 99, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 8, 0, 9, 10, 0, 4, 0, 10, 1, 0, 6, 105, 110, 118, 111, 107, 101, 1, 0, 49, 40, 76, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 59, 41, 86, 1, 0, 44, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 101, 118, 101, 110, 116, 76, 105, 115, 116, 101, 110, 101, 114, 115, 47, 69, 118, 101, 110, 116, 7, 0, 14, 1, 0, 32, 99, 111, 109, 47, 103, 115, 113, 117, 97, 114, 101, 100, 120, 99, 47, 104, 121, 115, 107, 121, 65, 80, 73, 47, 72, 121, 115, 107, 121, 65, 80, 73, 7, 0, 16, 1, 0, 4, 116, 101, 115, 116, 12, 0, 18, 0, 13, 10, 0, 17, 0, 19, 1, 0, 4, 67, 111, 100, 101, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 0, 33, 0, 2, 0, 4, 0, 1, 0, 6, 0, 0, 0, 2, 0, 1, 0, 8, 0, 9, 0, 1, 0, 21, 0, 0, 0, 17, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 11, -79, 0, 0, 0, 0, 0, 1, 0, 12, 0, 13, 0, 1, 0, 21, 0, 0, 0, 20, 0, 1, 0, 2, 0, 0, 0, 8, 43, -64, 0, 15, -72, 0, 20, -79, 0, 0, 0, 0, 0, 1, 0, 22, 0, 0, 0, 2, 0, 7}
}
