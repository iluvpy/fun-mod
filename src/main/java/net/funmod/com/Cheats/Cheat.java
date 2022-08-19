package net.funmod.com.Cheats;

public abstract class Cheat {
    private String name;
    private String category;
    private boolean enabled;

    public Cheat(String name, String category) {
        this.name = name;
        this.category = category;
        enabled = false;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
