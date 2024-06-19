package com.example.lista_usuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorUsuarios extends ArrayAdapter<Usuario>{
        public AdaptadorUsuarios(Context context, ArrayList<Usuario> datos) {
            super(context, R.layout.layout_item, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.layout_item, null);

            TextView LblNombre = (TextView)item.findViewById(R.id.lblNombre);
            LblNombre.setText(getItem(position).getNombres());

            TextView txtEmail = (TextView)item.findViewById(R.id.lblEmail);
            txtEmail.setText(getItem(position).getEmail());

            TextView txtWeb = (TextView)item.findViewById(R.id.lblweb);
            txtWeb.setText(getItem(position).getWebsite());

            ImageView imageView = (ImageView)item.findViewById(R.id.imgUsr);
            Glide.with(this.getContext())
                    .load(getItem(position).getUrlavatar())
                    .into(imageView);

            return(item);
        }
}

