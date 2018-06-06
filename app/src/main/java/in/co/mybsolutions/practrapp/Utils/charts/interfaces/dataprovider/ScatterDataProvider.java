package in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider;

import in.co.mybsolutions.practrapp.Utils.charts.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
