package in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider;

import in.co.mybsolutions.practrapp.Utils.charts.components.YAxis;
import in.co.mybsolutions.practrapp.Utils.charts.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
