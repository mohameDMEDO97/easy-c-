package Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.easyc.R;

import java.util.ArrayList;

import Controllers.SignInUpController;
import Interfaces.OnTaskListeners;

public class show_Requests extends AppCompatActivity {

    ArrayList<String> names;
    ArrayList<Integer> ids;
    SignInUpController signInUpController;
    ListView listView;


    public static final String REQUEST = "REQUEST";
    public static final String ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__requests);
        names = new ArrayList<String>();
        ids = new ArrayList<Integer>();
        signInUpController = new SignInUpController();
        listView = findViewById(R.id.requestsId);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToRequestView(ids.get(position));

            }
        });


        fillListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListView();
    }

    void fillListView() {
        signInUpController.getRequests(new OnTaskListeners.IdAndList() {
            @Override
            public void onSuccess(ArrayList<Integer> id, ArrayList<Object> result) {
                ids = id;
                names = (ArrayList<String>) (Object) result;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
                listView.setAdapter(adapter);
            }
        });
    }

    //open the selected request
    void goToRequestView(int id) {
        Intent intent = new Intent(getApplicationContext(), showRequest.class);
        intent.putExtra(ID, id);
        startActivity(intent);
    }


}
