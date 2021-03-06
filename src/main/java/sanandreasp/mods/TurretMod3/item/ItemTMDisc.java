package sanandreasp.mods.turretmod3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class ItemTMDisc extends ItemRecord {
	public ItemTMDisc(String par2Str) {
		super(par2Str);
	}

    @SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("TurretMod3:record_" + this.recordName);
    }

    @Override
    public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation("turretmod3:"+name);
    }
}
