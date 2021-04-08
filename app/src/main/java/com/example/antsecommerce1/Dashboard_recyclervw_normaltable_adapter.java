package com.example.antsecommerce1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Dashboard_recyclervw_normaltable_adapter extends RecyclerView.Adapter<Dashboard_recyclervw_normaltable_adapter.Viewholder> {
    private Context context;
    private int selected=-1;

    private ArrayList<Productdetails_sellerid_categoryid_responseBody1> Data_Lot_no_Report;
    private  Productdetails_sellerid_categoryid_responseBody1 productdetailsSelleridCategoryidResponseBody1;
    //private ArrayList<Fwreportgetlotnodata> Fw_Lot_no_Report;

    public Dashboard_recyclervw_normaltable_adapter(Context context, ArrayList<Productdetails_sellerid_categoryid_responseBody1> Data_Lot_no_Report) {
        this.context = context;

        this.Data_Lot_no_Report = Data_Lot_no_Report;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manageproductdetails_custom_layout,parent,false);
        return new Viewholder(view);





    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int i) {
        //holder.txt_quatity.setVisibility(View.VISIBLE);

        Productdetails_sellerid_categoryid_responseBody1 fwlogdata = Data_Lot_no_Report.get(i);

        holder.productvariantname.setText(fwlogdata.getProductVariantName());
        holder.skuidnumber.setText(fwlogdata.getSku());
        holder.price.setText(String.valueOf(fwlogdata.getPrice()));
        holder.date.setText(fwlogdata.getMrp());
       // holder.productimg.setImageResource(R.id.productimg1);
        Picasso.get().load(fwlogdata.getProductImage1()).into(holder.productimg);
        holder.availabilitycount.setText(fwlogdata.getAvailabilityCount());
        holder.active.setText(String.valueOf(fwlogdata.getIsActive()));

/*
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // get position
                int pos = getAdapterPosition();

                // check if item still exists
                if(pos != RecyclerView.NO_POSITION){
                    RvDataItem clickedDataItem = dataItems.get(pos);
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return Data_Lot_no_Report.size();
    }
    
    public void filterList(ArrayList<Productdetails_sellerid_categoryid_responseBody1> filteredList){

        Data_Lot_no_Report = filteredList;
        notifyDataSetChanged();

    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView productvariantname,skuidnumber,price,date,edit,availabilitycount,active,variantidtv;
       // Boolean activez;
        ImageView productimg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
           // =itemView.findViewById(R.id.checkbox_ftwt_fw);
            productvariantname=itemView.findViewById(R.id.productvariantnameid);
            skuidnumber=itemView.findViewById(R.id.number1);
            price=itemView.findViewById(R.id.rupeesnumber);
            date=itemView.findViewById(R.id.date1);
            edit=itemView.findViewById(R.id.edit1);
            availabilitycount=itemView.findViewById(R.id.piecesavalablecount);
            productimg=itemView.findViewById(R.id.productimg1);
            active =itemView.findViewById(R.id.active1);

          //  activez=Boolean.valueOf(String.valueOf(itemView.findViewById(R.id.active1)));
            variantidtv=itemView.findViewById(R.id.variantid);

           // variantid
            edit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){

                        Productdetails_sellerid_categoryid_responseBody1 clickedDataItem = Data_Lot_no_Report.get(pos);
                        Manage_Productlist_Screen manageProductlistScreen = new Manage_Productlist_Screen();

                        Intent i = new Intent(context,ProductDetailsEdit_Screen.class);
                        i.putExtra("productvariantid",String.valueOf(clickedDataItem.getProductVariantId()));
                        i.putExtra("VariantName",clickedDataItem.getProductVariantName());
                        i.putExtra("AvailabilityCount",clickedDataItem.getAvailabilityCount());
                        i.putExtra("mrpr",clickedDataItem.getMrp());
                        i.putExtra("Price",String.valueOf(clickedDataItem.getPrice()));
                        i.putExtra("sku",clickedDataItem.getSku());
                        i.putExtra("sellerid",manageProductlistScreen.sellerid_s);

                        // i.putExtra("customer1", usertypeuser);
                        Log.d("name", clickedDataItem.getProductVariantName());
                        Log.d("variantid", String.valueOf(clickedDataItem.getProductVariantId()));
                        Log.d("count", clickedDataItem.getAvailabilityCount());
                        Log.d("discount", clickedDataItem.getPercentageDiscount());
                        Log.d("mrpr", clickedDataItem.getMrp());
                        Log.d("Price", String.valueOf(clickedDataItem.getPrice()));

                       /* Log.d("name", clickedDataItem.getProductImage1());
                        Log.d("name", clickedDataItem.getUnitInStock());
                        Log.d("name", clickedDataItem.getUnitWeight());
                        Log.d("name", String.valueOf(clickedDataItem.getProductCategory()));*/
                      /*  Log.d("hgjjhjh", clickedDataItem.getProductVariantName());
                        Log.d("hgjjhjh", clickedDataItem.getProductVariantName());
                        Log.d("hgjjhjh", clickedDataItem.getProductVariantName());*/

                        context.startActivity(i);
                       /* Toast.makeText(v.getContext(), "" + clickedDataItem.getProductVariantId(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "" + clickedDataItem.getProductVariantName(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "" + clickedDataItem.getMrp(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "" + clickedDataItem.getSku(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "" + clickedDataItem.getUnitWeight(), Toast.LENGTH_SHORT).show();*/
                    }
                }
            });



        }
    }
}
