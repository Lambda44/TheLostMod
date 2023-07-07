package thelost.relics;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import thelost.character.TheLost;

import java.util.ArrayList;

import static thelost.BasicMod.makeID;

public class CrackedKey extends BaseRelic{
    private static final String NAME = "CrackedKey";
    public static final String ID = makeID(NAME);
    private AbstractCreature owner = null;

    public CrackedKey() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onPlayerEndTurn() {
        if(this.owner != null) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Buffer"));
        }
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof MonsterRoom) {
            this.flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        this.owner = AbstractDungeon.player;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
        this.owner = null;
    }

}
