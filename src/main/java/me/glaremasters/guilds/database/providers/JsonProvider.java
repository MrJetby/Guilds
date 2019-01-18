package me.glaremasters.guilds.database.providers;

import me.glaremasters.guilds.database.DatabaseProvider;
import me.glaremasters.guilds.guild.Guild;
import net.reflxction.simplejson.configuration.DirectConfiguration;
import net.reflxction.simplejson.json.JsonFile;
import net.reflxction.simplejson.json.JsonReader;
import net.reflxction.simplejson.json.JsonWriter;

import java.io.*;
import java.util.*;

/**
 * Created by GlareMasters
 * Date: 7/18/2018
 * Time: 11:38 AM
 */
public class JsonProvider implements DatabaseProvider {

    private File dataFolder;

    //todo

    public JsonProvider(File dataFolder) {
        this.dataFolder = new File(dataFolder, "data");
        //noinspection ResultOfMethodCallIgnored
        this.dataFolder.mkdir();

    }

    @Override
    public List<Guild> loadGuilds() throws IOException {
        List<Guild> loadedGuilds = new ArrayList<>();

        for (File file : Objects.requireNonNull(dataFolder.listFiles())){
            JsonReader reader = new JsonReader(new JsonFile(file));
            loadedGuilds.add(reader.deserializeAs(Guild.class));
            reader.close();
        }

        return loadedGuilds;
    }

    @Override
    public void saveGuilds(List<Guild> guilds) throws IOException {
        for (Guild guild : guilds){
            JsonWriter writer = new JsonWriter(new JsonFile(new File(dataFolder, guild.getId() + ".json")));
            writer.writeAndOverride(guild, true);
        }
    }

}
