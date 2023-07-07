package com.asrori201011401455.uas_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailWisata extends AppCompatActivity {
    String namaWisata;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        TextView tv_caption = findViewById(R.id.tv_caption);
        TextView tv_alamat = findViewById(R.id.tv_alamat);
        TextView tv_telp = findViewById(R.id.tv_telp);
        ImageView iv_wisata = findViewById(R.id.iv_wisata);
        Button btn_maps = findViewById(R.id.btn_maps);

        namaWisata = getIntent().getStringExtra("namaWisata");

        database = FirebaseDatabase.getInstance().getReference("destinasi_wisata");
        Query query = database.orderByChild("nama").equalTo(namaWisata);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String caption = dataSnapshot.child("nama").getValue().toString();
                    tv_caption.setText(caption);
                    String alamat = dataSnapshot.child("alamat").getValue().toString();
                    tv_alamat.setText(alamat);
                    String no_telp = dataSnapshot.child("telp").getValue().toString();
                    tv_telp.setText(no_telp);
                    String gambar = dataSnapshot.child("gambar").getValue().toString();
                    Picasso.get().load(gambar).into(iv_wisata);
                    Double longitude = dataSnapshot.child("map").child("long").getValue(Double.class);
                    Double latitude = dataSnapshot.child("map").child("lat").getValue(Double.class);

                    btn_maps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences sp = getSharedPreferences("MySp", MODE_PRIVATE);
                            SharedPreferences.Editor e = sp.edit();
                            e.putString("lat", String.valueOf(latitude));
                            e.putString("long", String.valueOf(longitude));
                            e.apply();

                            Intent i = new Intent(DetailWisata.this, MapActivity.class);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}