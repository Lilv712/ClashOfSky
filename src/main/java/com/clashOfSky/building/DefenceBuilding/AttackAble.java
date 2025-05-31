package com.clashOfSky.building.DefenceBuilding;

import com.clashOfSky.building.Incidence;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

//可以攻击其他实体的对象
public interface AttackAble {
    Incidence getAttackIncidence();
    void attack(Player player);
    void getAttackFreq();
    void getAttackDamage();
    void BuildingManagerRegister();
}
