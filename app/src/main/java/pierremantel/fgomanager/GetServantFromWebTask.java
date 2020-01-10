package pierremantel.fgomanager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

//AsyncTask retrieving the data about servants
public class GetServantFromWebTask extends AsyncTask<Void, Void, ArrayList<Servant>> {

    private Activity activity;
    private ArrayList<Servant> servants;

    public GetServantFromWebTask( Activity activity) {
        this.activity= activity;
    }

    @Override
    protected ArrayList<Servant> doInBackground(Void... voids) {

       servants = new ArrayList<>();

       //There are multiple pages to parse :
       ArrayList<String> pagesToParse = new ArrayList<>();
       String firstPage = "https://fategrandorder.fandom.com/wiki/Sub:Servant_List_by_ID/1-100";
       pagesToParse.add(firstPage);
       String secondPage = "https://fategrandorder.fandom.com/wiki/Sub:Servant_List_by_ID/101-200";
       pagesToParse.add(secondPage);
       String thirdPage = "https://fategrandorder.fandom.com/wiki/Sub:Servant_List_by_ID/201-300";
       pagesToParse.add(thirdPage);

       //Parsing  the pages
        for(String page: pagesToParse) {
            try {
                Document doc = Jsoup.connect(page).get();
                Elements test = doc.select("tr");
                Elements son = test.select(":has(td)");
                for (Element e : son) {
                    Elements infos = e.select("td");
                    //retrieving the icon
                    Elements icon = infos.select("img");
                    icon = icon.not("[alt=\"Locked\"], [alt=\"Limited\"], [alt=\"Gift Icon\"]");
                    String url = icon.attr("data-src");
                        //the first items of the list have a slightly different syntax
                    if (url.equals("")) {
                        url = icon.attr("src");
                    }
                        //the url in the html is a resized version; this line tweak the extracted url to the url for the regular sized picture
                    String trueUrl = url.split("png")[0] + "png";
                    //retrieving name then rarity
                    String name = infos.get(1).text();
                    String rarity = infos.get(2).text();
                    //retrieving the id
                    String id_string = infos.get(3).text();
                    Integer id = Integer.parseInt(id_string);
                    //adding the servant to the list of servants
                    Servant servant = new Servant(id, trueUrl, name, rarity);
                    servants.add(servant);
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return servants;
    }


    @Override
    protected void onPostExecute(ArrayList<Servant> result) {
        super.onPostExecute(result);
        Toast.makeText(
                activity.getApplicationContext(),
                R.string.update_database_end,
                Toast.LENGTH_LONG).show();
        //sending the result to ListServantActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Servant_list", result);
        activity.setResult(Activity.RESULT_OK, resultIntent);
        activity.finish();




    }
}
