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

package me.glaremasters.guilds.api;

import lombok.AllArgsConstructor;
import me.glaremasters.guilds.guild.Guild;
import me.glaremasters.guilds.guild.GuildCode;
import me.glaremasters.guilds.guild.GuildHandler;
import me.glaremasters.guilds.guild.GuildRole;
import me.glaremasters.guilds.guild.GuildTier;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GuildsAPI {

    private final GuildHandler guildHandler;

    /**
     * Get the guild of a player
     * @param player the players you're getting the guild of
     * @return the guild that the player is in
     */
    public Guild getGuild(@NotNull OfflinePlayer player) {
        return guildHandler.getGuild(player);
    }

    /**
     * Get the guild of a player by uuid
     * @param uuid uuid of the player
     * @return the guild they are in
     */
    public Guild getGuild(@NotNull UUID uuid) {
        return guildHandler.getGuild(uuid);
    }

    /**
     * Get the guild of a player by their name
     * @param name the name of the player
     * @return the guild they are in
     */
    public Guild getGuild(@NotNull String name) {
        return guildHandler.getGuild(name);
    }

    /**
     * Get the guild master of a guild
     * @param player the player you're getting the guild of
     * @return uuid of the guild master
     */
    public UUID getGuildMaster(@NotNull Player player) {
        return getGuild(player).getGuildMaster().getUuid();
    }

    /**
     * Get the amount of members in a guild
     * @param player the player you're getting the guild of
     * @return amount of members in the guild
     */
    public int getGuildMemberCount(@NotNull Player player) {
        return getGuild(player).getSize();
    }

    /**
     * Get amount of members online
     * @param player member
     * @return amount of members online
     */
    public int getGuildMembersOnline(@NotNull Player player) {
        return guildHandler.getGuild(player).getOnlineMembers().size();
    }

    /**
     * Status of a guild
     * @param player status
     * @return the status of a guild
     */
    public Guild.Status getGuildStatus(@NotNull Player player) {
        return getGuild(player).getStatus();
    }

    /**
     * Prefix of a guild
     * @param player prefix
     * @return the prefix of a guild
     */
    public String getGuildPrefix(@NotNull Player player) {
        return getGuild(player).getPrefix();
    }

    /**
     * Get the role of a player
     * @param player role
     * @return the role of a player
     */
    public GuildRole getGuildRole(@NotNull Player player) {
        return getGuild(player).getMember(player.getUniqueId()).getRole();
    }

    /**
     * Get the tier of a guild
     * @param player tier
     * @return the tier of a guild
     */
    public GuildTier getGuildTier(@NotNull Player player) {
        return getGuild(player).getTier();
    }

    /**
     * Get the bank balance of a guild
     * @param player balance
     * @return the balance of the guild
     */
    public double getBankBalance(@NotNull Player player) {
        return getGuild(player).getBalance();
    }

    /**
     * Get the guild codes of a guild
     * @param player the player the guild is in
     * @return the list of guild codes
     */
    public List<GuildCode> getGuildCodes(@NotNull Player player) {
        return getGuild(player).getCodes();
    }

    /**
     * Get the ID of a guild
     * @param player the player to check
     * @return the id of the guild they are in
     */
    public String getGuildId(@NotNull Player player) {
        return getGuild(player).getId().toString();
    }

}
