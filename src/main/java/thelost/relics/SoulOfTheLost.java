package thelost.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import thelost.cards.Dogma;
import thelost.character.TheLost;

import static thelost.BasicMod.makeID;

public class SoulOfTheLost extends BaseRelic{
    private static final String NAME = "SoulOfTheLost";
    public static final String ID = makeID(NAME);

    public SoulOfTheLost() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onChestOpen(boolean bossChest) {
        int roll = AbstractDungeon.treasureRng.random(0, 1);
        if (!bossChest && roll == 1) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            if (AbstractDungeon.relicRng.randomBoolean(0.75F)) {
                AbstractDungeon.getCurrRoom().addRelicToRewards(RelicTier.COMMON);
            } else {
                AbstractDungeon.getCurrRoom().addRelicToRewards(RelicTier.UNCOMMON);
            }
        }

    }

    public void onEnterRoom(AbstractRoom room) {
        if(AbstractDungeon.player.maxHealth > 1) {
            AbstractDungeon.player.maxHealth = 1;
        }
        if(AbstractDungeon.player.currentHealth > 1) {
            AbstractDungeon.player.currentHealth = 1;
        }
    }

    public void atBattleStartPreDraw() {
        if (AbstractDungeon.floorNum == 55) {
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeTempCardInHandAction(new Dogma(), 1, false));
        }
    }

}
