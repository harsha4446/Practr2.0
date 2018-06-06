package in.co.mybsolutions.practrapp.Utils.charts.formatter;

import in.co.mybsolutions.practrapp.Utils.charts.interfaces.datasets.ILineDataSet;
import in.co.mybsolutions.practrapp.Utils.charts.interfaces.dataprovider.LineDataProvider;

/**
 * Interface for providing a custom logic to where the filling line of a LineDataSet
 * should end. This of course only works if setFillEnabled(...) is set to true.
 * 
 * @author Philipp Jahoda
 */
public interface IFillFormatter
{

    /**
     * Returns the vertical (y-axis) position where the filled-line of the
     * LineDataSet should end.
     * 
     * @param dataSet the ILineDataSet that is currently drawn
     * @param dataProvider
     * @return
     */
    float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider);
}
