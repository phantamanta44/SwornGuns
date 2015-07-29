package net.dmulloy2.swornguns.io;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import lombok.Getter;
import net.dmulloy2.io.IOUtil;
import net.dmulloy2.swornguns.SwornGuns;
import net.dmulloy2.swornguns.types.Attachment;
import net.dmulloy2.util.NumberUtil;
import net.dmulloy2.util.Util;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Phanta
 */

public class AttachmentReader {

	private @Getter boolean loaded;
	private @Getter Attachment att;

	private final File file;
	private final SwornGuns plugin;
	public AttachmentReader(SwornGuns plugin, File file)
	{
		this.plugin = plugin;
		this.file = file;

		this.att = new Attachment(file.getName(), plugin);
		this.att.setFileName(file.getName().toLowerCase());
		this.load();
	}
	
	public final void load()
	{
		try
		{
			List<String> lines = IOUtil.readLines(file);
			for (String line : lines)
				computeData(line);
		}
		catch (Throwable ex)
		{
			plugin.getLogHandler().log(Level.SEVERE, Util.getUsefulStack(ex, "loading gun: " + file.getName()));
			return;
		}

		// Loading was successful
		this.loaded = true;
	}
	
	public final void computeData(String str) {
		if (str.indexOf("=") > 0)
		{
			String var = str.substring(0, str.indexOf("=")).toLowerCase();
			String val = str.substring(str.indexOf("=") + 1);
			if (var.equals("name"))
				att.setName(val);
			if (var.equals("ammomod"))
				att.setAmmoModifier(NumberUtils.toInt(val));
			if (var.equals("zoommod"))
				att.setZoomModifier(NumberUtils.toInt(val));
			if (var.equals("bulletdelaymod"))
				att.setBulletDelayModifier(NumberUtils.toInt(val));
			if (var.equals("recoilmod"))
				att.setRecoilModifier(NumberUtils.toDouble(val));
			if (var.equals("accuracyaimedmod"))
				att.setZoomedAccuracyModifier(NumberUtils.toDouble(val));
			if (var.equals("accuracymod"))
				att.setUnzoomedAccuracyModifier(NumberUtils.toDouble(val));
			if (var.equals("volumemod"))
				att.setVolumeModifier(NumberUtils.toDouble(val));
			if (var.equals("pitchmod"))
				att.setPitchModifier(NumberUtils.toDouble(val));
			if (var.equals("bulletspeedmod"))
				att.setBulletSpeedModifier(NumberUtils.toDouble(val));
			if (var.equals("bipod"))
				att.setBipod(Util.toBoolean(val));
			if (var.equals("light"))
				att.setProvidingLight(Util.toBoolean(val));
			if (var.equals("laser"))
				att.setLaser(Util.toBoolean(val));
			if (var.equals("smoke"))
				att.setProducingSmoke(Util.toBoolean(val));
			if (var.equals("suppresssmoke"))
				att.setHidingSmoke(Util.toBoolean(val));
			if (var.equals("damageoverride"))
				att.setNewDmg(NumberUtil.toDouble(val));
			if (var.equals("bulletsperclickoverride"))
				att.setNewShotsPerClick(NumberUtil.toInt(val));
			if (var.equals("description"))
				att.setDescription(val);
		}
	}
	
}
