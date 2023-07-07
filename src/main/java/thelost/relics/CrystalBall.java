package thelost.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import thelost.character.TheLost;

import static thelost.BasicMod.makeID;

public class CrystalBall extends BaseRelic{
    private static final String NAME = "CrystalBall";
    public static final String ID = makeID(NAME);

    public CrystalBall() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.RARE, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 2), 2));
    }

}
