package tjobah.a9;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.text.NumberFormat;
import java.util.Locale;

public class Coin extends BaseObservable{
    private String name;
    private double curValue;

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * constructor
     * @param name
     */
    public Coin(String name) {
        this.name = name;
    }

    /**
     * gets name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the current value of the coin
     * @return
     */
    @Bindable
    public String getCurValue() {
        return numberFormat.format(curValue);
    }

    /**
     * sets the current value of a coin
     * @param curValue
     */
    public void setCurValue(double curValue) {
        this.curValue = curValue;
        notifyPropertyChanged(BR.curValue);
    }
}
