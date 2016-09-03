package de.wolfi.minopoly.components;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.wolfi.minopoly.Main;
import de.wolfi.minopoly.components.fields.Field;
import de.wolfi.minopoly.utils.DisguiseManager;
import de.wolfi.minopoly.utils.FigureType;
import de.wolfi.minopoly.utils.MapFactory;
import de.wolfi.minopoly.utils.Messages;
import de.wolfi.minopoly.utils.TeleportCause;

public class Player{

	private final org.bukkit.entity.Player hook;
	private final FigureType type;
	
	private Bank money;
	
	private Field location;
	
	private Entity tmp;
	
	private final Minopoly game;
	
	public Player(org.bukkit.entity.Player hook, FigureType t, Minopoly game){
		this.hook = hook;
		this.type = t;
		this.game = game;
	}

	
	public FigureType getFigure() {
		return this.type;
	}

	@Override
	public boolean equals(Object compare) {
		if(!(compare instanceof Player)) return false;
		return this.getFigure() == ((Player) compare).getFigure();
	}
	
	public void teleport(Location to, TeleportCause cause){
		hook.teleport(to);
		if(cause == TeleportCause.MINIGAME_STARTED){
			DisguiseManager.disguise(hook, type);
			if(tmp != null) tmp.remove();
		}else if(cause == TeleportCause.MINIGAME_END){
			DisguiseManager.vanish(hook);
			spawnFigure();
		}else if(cause == TeleportCause.MINIGAME_ACTION){
			Messages.TELEPORT.send(hook);
		}
	}
	
	
	
	private void spawnFigure() {
		Entity e = location.getWorld().spawnEntity(location.getLocation(), type.getEntityType());
		e.setCustomName(hook.getName());
		e.setCustomNameVisible(true);
		e.setMetadata("PlayerNPC", new FixedMetadataValue(Main.getMain(), this));
		if(e instanceof LivingEntity){
			((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*60*60, 20));
		}
		tmp = e;
	}


	public String getName(){
		return hook.getName();
	}
	
	protected org.bukkit.entity.Player getHook() {
		return hook;
	}
	
	public void setInventory(){
		hook.getInventory().clear();
		hook.sendMap(MapFactory.getMap(game, hook));
		
	}
	
	public void addMoney(int amount){
		this.addMoney(amount, "Grundlos");
	}
	
	public void addMoney(int amount, String reason){
		this.money.addMoney(this, amount);
		Messages.MONEY_GAIN.send(hook, String.valueOf(amount),reason);
	}
	
	public void transferMoneyTo(Player player, String reason){
		
	}
	
}
