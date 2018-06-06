package in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider;

import in.co.mybsolutions.practrapp.Utils.charts.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
