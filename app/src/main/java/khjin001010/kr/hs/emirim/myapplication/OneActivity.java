package khjin001010.kr.hs.emirim.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



class Data {
    private String src;
    private String title;
    private String value;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Data{" +
                "src='" + src + '\'' +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

public class OneActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
   // ArrayList
    ListView list;
   // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, musics);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        database = FirebaseDatabase.getInstance();
        list = (ListView)findViewById(R.id.list);
        // myRef = database.getReference("key");
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */

        for(int i=1; i<3;i++) {
            myRef = database.getReference("key"+i);

            if(myRef==null){
                return;
            }

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Data d = dataSnapshot.getValue(Data.class);
                    Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_SHORT).show();
                    // String s = dataSnapshot.getValue(String.class);
                    // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
