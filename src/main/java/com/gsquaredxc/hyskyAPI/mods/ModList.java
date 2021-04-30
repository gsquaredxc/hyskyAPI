package com.gsquaredxc.hyskyAPI.mods;

import java.util.ArrayList;

public class ModList {

    private final ArrayList<Mod> listOfMods = new ArrayList<>();

    public void addModToList(Mod e){
        listOfMods.add(e);
    }

    public ArrayList<Mod> getModList(){
        return listOfMods;
    }



}
