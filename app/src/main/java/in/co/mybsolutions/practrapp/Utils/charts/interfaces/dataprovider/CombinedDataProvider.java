package in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider;

import in.co.mybsolutions.practrapp.Utils.charts.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
