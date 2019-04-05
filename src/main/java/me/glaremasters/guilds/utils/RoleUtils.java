/*
 * MIT License
 *
 * Copyright (c) 2018 Glare
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.glaremasters.guilds.utils;

import me.glaremasters.guilds.guild.Guild;
import me.glaremasters.guilds.guild.GuildHandler;
import me.glaremasters.guilds.guild.GuildMember;
import org.bukkit.OfflinePlayer;

/**
 * Created by Glare
 * Date: 4/4/2019
 * Time: 11:35 PM
 */
public class RoleUtils {

    /**
     * Simple method to check if a user is in the same guild
     * @param guild the guild being checked
     * @param player the player being checked
     * @return if in guild or not
     */
    public static boolean inGuild(Guild guild, OfflinePlayer player) {
        return guild.getMember(player.getUniqueId()) != null;
    }

    /**
     * Simple method to check if the player being promote can be promoted
     * @param guild the guild of the player
     * @param player the player being checked
     * @return can be promoted or not
     */
    public static boolean canPromote(Guild guild, OfflinePlayer player) {
        return guild.getMember(player.getUniqueId()).getRole().getLevel() <= 1;
    }

    /**
     * Simple method to promote a user
     * @param guildHandler the guild handler
     * @param guild the guild of the player
     * @param player the player
     */
    public static void promote(GuildHandler guildHandler, Guild guild, OfflinePlayer player) {
        GuildMember member = guild.getMember(player.getUniqueId());
        member.setRole(guildHandler.getGuildRole(member.getRole().getLevel() - 1));
    }

    /**
     * Get the name of the role
     * @param member the member being checked
     * @return the name of role
     */
    public static String getCurrentRoleName(GuildMember member) {
        return member.getRole().getName();
    }

    /**
     * Get the role of a user before they were promoted
     * @param guildHandler guild handler
     * @param member the member being looked at
     * @return the name of the pre promoted role name
     */
    public static String getPrePromotedRoleName(GuildHandler guildHandler, GuildMember member) {
        return guildHandler.getGuildRole(member.getRole().getLevel() + 1).getName();
    }

    /**
     * Get the role of a user before they were demoted
     * @param guildHandler the guild handler
     * @param member the member being looked at
     * @return name of pre demoted role
     */
    public static String getPreDemotedRoleName(GuildHandler guildHandler, GuildMember member) {
        return guildHandler.getGuildRole(member.getRole().getLevel() - 1).getName();
    }

}
