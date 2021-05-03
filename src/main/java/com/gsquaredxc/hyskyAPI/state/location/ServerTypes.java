package com.gsquaredxc.hyskyAPI.state.location;

public enum ServerTypes {
    BlazingFortress("Blazing Fortress","combat_2"),
    DeepCaverns("Deep Caverns","mining_2"),
    Dungeon("Dungeon: Catacombs","dungeon"),
    DungeonHub("Dungeon Hub","dungeon_hub"),
    DwarvenMines("Dwarven Mines","mining_3"),
    FarmingIsland("The Farming Islands","farming_1"),
    GoldMine("Gold Mine","mining_1"),
    Hub("Hub","hub"),
    TheEnd("The End","combat_3"),
    ThePark("The Park","foraging_1"),
    PrivateIsland("Private World","dynamic"),
    SpiderDen("Spider's Den","combat_1"),
    UNKNOWN("","");

    public final String tab;
    public final String locraw;

    ServerTypes(final String tab, final String locraw) {
        this.tab = tab;
        this.locraw = locraw;
    }

    public static ServerTypes getFromTab(final String tab){
        for (final ServerTypes s: ServerTypes.values()){
            if (s.tab.equals(tab)){
                return s;
            }
        }
        return UNKNOWN;
    }

    public static ServerTypes getFromLocraw(final String locraw){
        for (final ServerTypes s: ServerTypes.values()){
            if (s.tab.equals(locraw)){
                return s;
            }
        }
        return UNKNOWN;
    }
}
