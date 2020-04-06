package gg.scenarios.joust.managers.arena;

import gg.scenarios.joust.Joust;
import gg.scenarios.joust.managers.Tournament;
import gg.scenarios.joust.managers.TournamentMatch;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Arena {

    private Joust joust = Joust.getInstance();

    public static List<Arena> arenasList = new ArrayList<>();

    private String name;
    private Location spawn1, spawn2, specLocation;
    private boolean available;
    private TournamentMatch match;

    public Arena(String name, Location spawn1, Location spawn2, Location specLocation) {
        this.name = name;
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
        this.specLocation = specLocation;
        available = true;
        arenasList.add(this);
    }






    public void clear(){
        System.out.println("Started Clearing Arena: " + this.getName());

            Location loc1 = spawn1;
            loc1.add(0, 2, 0);
            Location loc2 = spawn2;
            loc2.add(0, -2, 0);
            for (Double x = loc1.getX(); x <= loc2.getX(); x++) {
                for (Double y = loc1.getY(); y <= loc2.getY(); y++) {
                    for (Double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                        Location lll = new Location(Bukkit.getWorld("world"), x.intValue() , y.intValue() , z.intValue());
                        if (lll.getBlock().getType() == Material.WATER || lll.getBlock().getType() == Material.LAVA || lll.getBlock().getType() == Material.STATIONARY_WATER || lll.getBlock().getType() == Material.LAVA_BUCKET || lll.getBlock().getType() == Material.STONE || lll.getBlock().getType() == Material.COBBLESTONE || lll.getBlock().getType() == Material.OBSIDIAN){
                            lll.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
        System.out.println("Finished Clearing Arena: " +getName());
    }

}