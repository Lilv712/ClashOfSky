package com.clashOfSky.building;

import org.bukkit.entity.Player;

//可以攻击其他实体的对象
public interface AttackAble {
    Incidence getAttackIncidence();
    void attack(Player player);
    void getAttackFreq();
}
