package thelost.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thelost.powers.DeliriousPower;
import thelost.powers.TearsPower;

import java.util.Iterator;

public class DeliriousPotion extends CustomPotion {
    public static final String POTION_ID = "thelost:DeliriousPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);;

    public DeliriousPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPHERE, PotionColor.WHITE);
        this.isThrown = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordProper("thelost:delirious")), GameDictionary.keywords.get("thelost:delirious")));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        this.addToBot(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            this.addToBot(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 0.5F));
            m.addPower(new DeliriousPower(m, this.potency));
        }
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return -1;
    }

    @Override
    public CustomPotion makeCopy() {
        return new DeliriousPotion();
    }
}
