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
    private boolean query_success = false;
    final double[] LONDPP = {0.00034332, 0.00017166, 0.00008583, 0.00004291, 0.00002145, 0.00001073, 0.00000536, 0.00000268}; // longitude distance per pixel
    final double D0_ULLON = -122.2998046875;
    final double D0_ULLAT = 37.892195547244356;
    final double D0_LRLON = -122.2119140625;
    final double D0_LRLAT = 37.82280243352756;

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
        int w, h;        // to store request's query parameters
        double ul_lat = params.get("ullat");        // upper left latitude
        double ul_lon = params.get("ullon");        // upper left longitude
        double lr_lat = params.get("lrlat");        // lower right latitude
        double lr_lon = params.get("lrlon");        // lower right longitude
        w = (params.get("w")).intValue();                // width in pixels
        h = params.get("h").intValue();                // height in pixels



        /**
         * determine depth's output images - the depth with greatest DonDPP that is less than or equal to LonDPP of querybox
         * store the depth to depth;
         */
        double requestLonDPP = Math.abs((lr_lon - ul_lon) / w);
        for (int i = 0; i < 8; i++) {
            if (LONDPP[i] <= requestLonDPP) {
                depth = i;
                break;
            }
        }
        if (LONDPP[7] > requestLonDPP) depth = 7;

        /**
         * computer render grid based on depth and input ul/lr longitude/latitude
         * parameters required: double ulLat, double ulLon, double lrLat, double lrLon, int width, int height, int depth
         */
        render_grid = generateRenderGrid(generateLocationOfOutputTiles(ul_lat,ul_lon, lr_lat, lr_lon, w, h, depth));
        setRasterLocation(generateLocationOfOutputTiles(ul_lat,ul_lon, lr_lat, lr_lon, w, h, depth));
        query_success = true;

        /**
         * all required output should be available now, so add return value to 'result' hashmap
         *      * @return A map of results for the front end as specified: <br>
         *      * "render_grid"   : String[][], the files to display. <br>
         *      * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
         *      * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
         *      * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
         *      * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
         *      * "depth"         : Number, the depth of the nodes of the rastered image <br>
         *      * "query_success" : Boolean, whether the query was able to successfully complete; don't
         */
        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("depth", depth);
        results.put("query_success", query_success);

        return results;
    }

    /**
     *
     * @param ulLat
     * @param ulLon
     * @param lrLat
     * @param lrLon
     * @param width
     * @param height
     * @param depth
     * @return a 2D array of strings containing a list of files which captures the region indicated by input params.

    private String[][] generateRenderGrid(double ulLat, double ulLon, double lrLat, double lrLon, int width, int height, int depth) {
        int D = depth;
        int ul_x, ul_y, lr_x, lr_y;
        double widthPerTile, heightPerTile;
        widthPerTile = (D0_LRLON - D0_ULLON)/(Math.pow(2, D));     // width per tile at D's depth; D is between 0 and 7;
        heightPerTile = (D0_ULLAT - D0_LRLAT)/(Math.pow(2, D));     // height per tile at D's depth; D is between 0 and 7;

        //calculate the location of start and end tiles
        ul_x = (int) ((ulLon - D0_ULLON)/widthPerTile);
        lr_x = (int) ((lrLon - D0_ULLON)/widthPerTile);
        ul_y = (int) ((D0_ULLAT - ulLat)/heightPerTile);
        lr_y = (int) ((D0_ULLAT - lrLat)/heightPerTile);
        int numOfxTiles, numOfyTiles;       // calculate how many tiles are required for the output;
        numOfxTiles = lr_x - ul_x + 1;
        numOfyTiles = lr_y - ul_y + 1;

        // Generate string name and add to the 2-D string array;
        // the file name should look like: "d0_x0_y0.png"
        String[][] render_grid = new String[numOfxTiles][numOfyTiles];
        int posX = ul_x;
        int posY = ul_y;
        for (int i = 0; i < numOfyTiles; i++) {
            for (int j = 0; j < numOfxTiles; j++) {
                render_grid[i][j] ="d" + D + "_" + "x" + posX + "_" + "y" + posY + ".png";
                posX++;
            }
            posX = ul_x;
            posY++;
        }
        return render_grid;
    }
    */

    /**
     *
     * @param ulLat
     * @param ulLon
     * @param lrLat
     * @param lrLon
     * @param width
     * @param height
     * @param depth
     * @return {ul_x, ul_y, lr_x, lr_y, depth}
     */
    private int[] generateLocationOfOutputTiles(double ulLat, double ulLon, double lrLat, double lrLon, int width, int height, int depth) {
        int D = depth;
        int ul_x, ul_y, lr_x, lr_y;
        double widthPerTile, heightPerTile;
        widthPerTile = (D0_LRLON - D0_ULLON)/(Math.pow(2, D));     // width per tile at D's depth; D is between 0 and 7;
        heightPerTile = (D0_ULLAT - D0_LRLAT)/(Math.pow(2, D));     // height per tile at D's depth; D is between 0 and 7;
        /**
         * calculate the location of start and end tiles
         */
        ul_x = (int) ((ulLon - D0_ULLON)/widthPerTile);
        lr_x = (int) ((lrLon - D0_ULLON)/widthPerTile);
        ul_y = (int) ((D0_ULLAT - ulLat)/heightPerTile);
        lr_y = (int) ((D0_ULLAT - lrLat)/heightPerTile);

        int[] locationOfOutputTiles = {ul_x, ul_y, lr_x, lr_y, depth};
        /*
        // code is refactored - separate output tile location generation and string 2D array generation
        int numOfxTiles, numOfyTiles;       // calculate how many tiles are required for the output;
        numOfxTiles = lr_x - ul_x + 1;
        numOfyTiles = lr_y - ul_y + 1;
        */


        /**
         * Generate string name and add to the 2-D string array;
         * the file name should look like: "d0_x0_y0.png"

        String[][] render_grid = new String[numOfxTiles][numOfyTiles];
        int posX = ul_x;
        int posY = ul_y;
        for (int i = 0; i < numOfyTiles; i++) {
            for (int j = 0; j < numOfxTiles; j++) {
                render_grid[i][j] ="d" + D + "_" + "x" + posX + "_" + "y" + posY + ".png";
                posX++;
            }
            posX = ul_x;
            posY++;
        }
         */
        return locationOfOutputTiles;
    }

    private String[][] generateRenderGrid(int[] locationOfOutputTiles) {
        int ul_x = locationOfOutputTiles[0];
        int ul_y = locationOfOutputTiles[1];
        int lr_x = locationOfOutputTiles[2];
        int lr_y = locationOfOutputTiles[3];
        int D = locationOfOutputTiles[4];       // depth

        int numOfxTiles, numOfyTiles;       // calculate how many tiles are required for the output;
        numOfxTiles = lr_x - ul_x + 1;
        numOfyTiles = lr_y - ul_y + 1;
        /**
         * Generate string name and add to the 2-D string array;
         * the file name should look like: "d0_x0_y0.png"
         */
        String[][] render_grid = new String[numOfyTiles][numOfxTiles];
        int posX = ul_x;
        int posY = ul_y;
        for (int i = 0; i < numOfyTiles; i++) {
            for (int j = 0; j < numOfxTiles; j++) {
                render_grid[i][j] ="d" + D + "_" + "x" + posX + "_" + "y" + posY + ".png";
                posX++;
            }
            posX = ul_x;
            posY++;
        }
        return render_grid;
    }

    /**
     * Generate Raster location (upper left LON/LAT and lower right LON/LAT in the output map)
     * @param locationOfOutputTiles {ul_x, ul_y, lr_x, lr_y, depth}
     * @return raster location {raster_ul_x, raster_ul_y, raster_lr_x, raster_lr_y}
     *     final double D0_ULLON = -122.2998046875;
     *     final double D0_ULLAT = 37.892195547244356;
     *     final double D0_LRLON = -122.2119140625;
     *     final double D0_LRLAT = 37.82280243352756;
     */
    private void setRasterLocation(int[] locationOfOutputTiles) {
        int ul_x = locationOfOutputTiles[0];        //
        int ul_y = locationOfOutputTiles[1];
        int lr_x = locationOfOutputTiles[2];
        int lr_y = locationOfOutputTiles[3];
        int depth = locationOfOutputTiles[4];
        double widthPerTile = (D0_LRLON - D0_ULLON)/Math.pow(2, depth);
        double heightPerTile = (D0_ULLAT - D0_LRLAT)/Math.pow(2, depth);
        double[] rasterLocation = new double[4];
        raster_ul_lon = D0_ULLON + ul_x * widthPerTile;
        raster_ul_lat = D0_ULLAT - ul_y * heightPerTile;
        raster_lr_lon = D0_ULLON + (lr_x + 1) * widthPerTile;
        raster_lr_lat = D0_ULLAT - (lr_y + 1) * heightPerTile;

    }


}
