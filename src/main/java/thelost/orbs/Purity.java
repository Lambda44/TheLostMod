package thelost.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbPassiveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thelost.character.TheLost;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import static thelost.BasicMod.characterPath;
import static thelost.BasicMod.makeID;

public class Purity extends CustomOrb {
    public static final String ORB_ID = makeID("Purity");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    //public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = characterPath("orbs/Purity.png");
    public static com.badlogic.gdx.graphics.Color color = Color.GOLDENROD.cpy();

    public Purity() {
        super(ORB_ID, NAME, 4, 1, "", "", IMG_PATH);
        updateDescription();
    }

    public void update(){
        super.update();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + orbString.DESCRIPTION[2];
    }

    @Override
    public void onEvoke() {
        CardCrawlGame.sound.play("POWER_FOCUS", 0.05F);
        AbstractPlayer player = AbstractDungeon.player;
        player.addPower(new FocusPower(player, 1));
        updateDescription();
    }

    public void onEndOfTurn() {
        updateDescription();
        AbstractDungeon.actionManager.addToBottom(new LightningOrbPassiveAction(new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), this, false));
    }

    @Override
    public void render(SpriteBatch sb) {
        this.shineColor.a = this.c.a / 2.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Purity();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);
    }
}
