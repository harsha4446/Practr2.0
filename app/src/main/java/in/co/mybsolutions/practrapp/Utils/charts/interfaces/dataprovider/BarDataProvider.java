package in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider;

import in.co.mybsolutions.practrapp.Utils.charts.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isHighlightFullBarEnabled();
}
