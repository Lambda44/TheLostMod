package thelost.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static thelost.BasicMod.makeID;

public class EvilPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Evil");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EvilPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }



    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 10) {
            this.flash();
            this.amount -= 10;

            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 2), 2));
            this.addToBot(new DrawCardAction(this.owner, 1));

            if (this.amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new EvilPower(owner, amount);
    }
}
