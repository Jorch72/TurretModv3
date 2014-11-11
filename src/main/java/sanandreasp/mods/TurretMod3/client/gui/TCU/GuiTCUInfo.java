package sanandreasp.mods.TurretMod3.client.gui.TCU;

import net.minecraft.entity.EntityList;

import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSForcefield;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgExperience;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;

public class GuiTCUInfo extends GuiTCUBase {
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();

		this.mc.getTextureManager().bindTexture(TM3ModRegistry.TEX_GUITCUDIR + "page_2.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        
        String s = this.turret != null ? this.turret.tInfo.getTurretName() : "";
        this.fontRendererObj.drawString("\247a"+s, this.guiLeft + (this.xSize - this.fontRendererObj.getStringWidth(s))/2, this.guiTop + 207, 0xFFFFFF);
        
        s = StatCollector.translateToLocal("gui.tcu.titInfo");
        this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 6, 0x808080);
        
        if (this.turret != null) {
        	TurretInfo tInf = TurretInfo.getTurretInfo(this.turret.getClass());
        	
		    s = StatCollector.translateToLocal("gui.tcu.infoHealth");
		    this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 22, 0xAA0000);

		    String s1 = StatCollector.translateToLocal("gui.tinfo.healthpts").split("\\|")[1];
		    s = turret.getSrvHealth() + " / " + turret.func_110138_aP() + " " + s1;
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 31, 0x600000);
		    
		    this.drawRect(this.guiLeft + 4, this.guiTop + 41, this.guiLeft + this.xSize - 4, this.guiTop + 42, 0xFFB0B0B0);
		    
		    if (this.turret instanceof EntityTurret_TSForcefield) {
			    s = StatCollector.translateToLocal("gui.tcu.infoShield");
			    this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 45, 0xAAAA00);
			    
			    s = ((EntityTurret_TSForcefield) this.turret).getShieldPts() + " / " + ((EntityTurret_TSForcefield) this.turret).getMaxShieldPts();
			    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 54, 0x606000);
		    } else {
			    s = StatCollector.translateToLocal("gui.tcu.infoAmmo");
			    this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 45, 0x0000AA);
			    
			    s1 = StatCollector.translateToLocal("gui.tinfo.projectiles").split("\\|")[1];
			    s = this.turret.getAmmo() + " / " + this.turret.getMaxAmmo() + " " + s1;
			    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 54, 0x000060);
        
			    s = StatCollector.translateToLocal("gui.tcu.infoType") + ":";
			    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 63, 0x000060);
			    s = tInf.getAmmoTypeNameFromIndex(this.turret.getAmmoType());
			    this.fontRendererObj.drawString(s, this.guiLeft + 18, this.guiTop + 72, 0x606060);
		    }
		    
		    this.drawRect(this.guiLeft + 4, this.guiTop + 82, this.guiLeft + this.xSize - 4, this.guiTop + 83, 0xFFB0B0B0);
		   
		    s = StatCollector.translateToLocal("gui.tcu.infoExp");
		    this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 86, 0x00AA00);
		    boolean isRightPlayer = this.turret.hasPlayerAccess(this.mc.thePlayer);
		    if (this.turret.canCollectXP() && isRightPlayer) {
		    	s = (isRightPlayer ? this.turret.getExperience() : "-") + " / " + (isRightPlayer ? this.turret.getExpCap() : "-") + " XP";
		    } else {
		    	s = StatCollector.translateToLocal("gui.tcu.infoExpNo");
		    }
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 95, 0x006000);
		    
		    this.drawRect(this.guiLeft + 4, this.guiTop + 105, this.guiLeft + this.xSize - 4, this.guiTop + 106, 0xFFB0B0B0);
		    
		    s = StatCollector.translateToLocal("gui.tcu.misc");
		    this.fontRendererObj.drawString(s, this.guiLeft + 6, this.guiTop + 109, 0x6000AA);
		    
		    String s2[] = StatCollector.translateToLocal("gui.tcu.miscUP").split("\\|");
		    s = s2[0] + ":";
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 118, 0x300060);
		    s = this.turret.getTurretName();
		    this.fontRendererObj.drawString(s, this.guiLeft + 18, this.guiTop + 127, 0x606060);
		    
		    s = s2[1] + ":";
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 136, 0x300060);
		    s = this.turret.getPlayerName();
		    this.fontRendererObj.drawString(s, this.guiLeft + 18, this.guiTop + 145, 0x606060);
		    
		    s = s2[2] + ":";
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 154, 0x300060);
		    s = (this.turret.getCurrentTargetStr().length() > 0 ? StatCollector.translateToLocal(this.turret.getCurrentTargetStr()) : s2[3]);
		    this.fontRendererObj.drawString(s, this.guiLeft + 18, this.guiTop + 163, 0x606060);
		    
		    s = s2[4] + ":";
		    this.fontRendererObj.drawString(s, this.guiLeft + 12, this.guiTop + 172, 0x300060);
		    s2 = StatCollector.translateToLocal("gui.tcu.yesNo").split("\\|");
		    s = (this.turret.isRiding() ? s2[0] : s2[1]);
		    this.fontRendererObj.drawString(s, this.guiLeft + 18, this.guiTop + 181, 0x606060);
        }
		super.drawScreen(par1, par2, par3);
	}
}
