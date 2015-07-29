package net.dmulloy2.swornguns.types;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dmulloy2.swornguns.SwornGuns;
import net.dmulloy2.util.FormatUtil;

/**
 * @author Phanta
 */

@Data
public class Attachment {

	private int ammoModifier = 0;
	private int zoomModifier = 0;
	private int bulletDelayModifier = 0;
	private double recoilModifier = 1.0D;
	private double zoomedAccuracyModifier = 1.0D;
	private double unzoomedAccuracyModifier = 1.0D;
	private double volumeModifier = 1.0D;
	private double pitchModifier = 1.0D;
	private double bulletSpeedModifier = 1.0D;
	private boolean bipod = false;
	private boolean providingLight = false;
	private boolean laser = false;
	private boolean hidingSmoke = false;
	private boolean producingSmoke = false;
	private double newDmg = -1.0D;
	private int newShotsPerClick = -1;
	
	private String name;
	private String fileName;
	private List<String> description = new ArrayList<String>();
	
	private final SwornGuns plugin;
	
	public Attachment(String name, SwornGuns plugin) {
		this.name = this.fileName = name;
		this.plugin = plugin;
	}
	
	public void setDescription(String val)
	{
		description.clear();

		for (String s : val.split(";"))
		{
			description.add(FormatUtil.format(s));
		}
	}
	
	@SuppressWarnings("serial")
	@Data
	@EqualsAndHashCode(callSuper=true)
	public static class AttachmentList extends ArrayList<Attachment> {
		
		private int ammoModifier = 0;
		private int zoomModifier = 0;
		private int bulletDelayModifier = 0;
		private double recoilModifier = 1.0D;
		private double zoomedAccuracyModifier = 1.0D;
		private double unzoomedAccuracyModifier = 1.0D;
		private double volumeModifier = 1.0D;
		private double pitchModifier = 1.0D;
		private double bulletSpeedModifier = 1.0D;
		private boolean bipod = false;
		private boolean providingLight = false;
		private boolean laser = false;
		private boolean hidingSmoke = false;
		private boolean producingSmoke = false;
		private double newDmg = -1.0D;
		private int newShotsPerClick = -1;
		
		public void update() {
			for (Attachment attach : toArray(new Attachment[0])) {
				ammoModifier += attach.getAmmoModifier();
				zoomModifier += attach.getZoomModifier();
				bulletDelayModifier += attach.getBulletDelayModifier();
				recoilModifier *= attach.getRecoilModifier();
				zoomedAccuracyModifier *= attach.getZoomedAccuracyModifier();
				unzoomedAccuracyModifier *= attach.getUnzoomedAccuracyModifier();
				volumeModifier *= attach.getVolumeModifier();
				pitchModifier *= attach.getPitchModifier();
				bulletSpeedModifier *= attach.getBulletSpeedModifier();
				bipod |= attach.isBipod();
				providingLight |= attach.isProvidingLight();
				laser |= attach.isLaser();
				hidingSmoke |= attach.isHidingSmoke();
				producingSmoke |= attach.isProducingSmoke();
				double newNewDmg = attach.getNewDmg();
				int newNewShots = attach.getNewShotsPerClick();
				newDmg = newNewDmg != -1.0D ? newNewDmg : newDmg;
				newShotsPerClick = newNewShots != -1 ? newNewShots : newShotsPerClick;
			}
		}
		
		@Override
		public boolean add(Attachment a) {
			if (super.add(a)) {
				update();
				return true;
			}
			return false;
		}
		
		@Override
		public boolean remove(Object o) {
			if (super.remove(o)) {
				update();
				return true;
			}
			return false;
		}
		
	}
	
}
