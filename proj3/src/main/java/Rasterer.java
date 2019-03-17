import java.util.HashMap;
import java.util.Map;
import java.math.*;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    /**
     * Fields
     */
    private String[][] render_grid;
    private double raster_ul_lon, raster_ul_lat, raster_lr_lon, raster_lr_lat;
    private int depth;
    private boolean query_success;
    final double[] LONDPP = {0.00034332, 0.00017166, 0.00008583, 0.00004291, 0.00002145, 0.00001073, 0.00000536, 0.00000268};

    public Rasterer() {
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        /**
         * define parameters
         */
        double ullat, ullon, lrlat, lrlon, w, h;        // to store request's query parameters
        ullat = params.get("ullat");        // upper left latitude
        ullon = params.get("ullon");        // upper left longitude
        lrlat = params.get("lrlat");        // lower right latitude
        lrlon = params.get("lrlon");        // lower right longitude
        w = params.get("w");                // width in pixels
        h = params.get("h");                // height in pixels

        /**
         * determine depth's output images - the depth with greatest DonDPP that is less than or equal to LonDPP of querybox
         * store the depth to depth;
         */
        double requestLonDPP = Math.abs((lrlon - ullon) / w);
        for (int i = 0; i < 8; i++) {
            if (LONDPP[i] <= requestLonDPP) {
                depth = i;
                break;
            }
        }
        if (LONDPP[7] > requestLonDPP) depth = 7;







        return results;
    }
}
