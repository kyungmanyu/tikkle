package com.example.tikkle;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageLoadAdapter  extends BaseAdapter implements OnClickListener{
    
   private Activity activity;
   private String[] data;
   private static LayoutInflater inflater=null;
   public ImageLoader imageLoader; 
    
   public ImageLoadAdapter(Activity a, String[] d) {
       activity = a;
       data=d;
       inflater = (LayoutInflater)activity.
                           getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
       // Create ImageLoader object to download and show image in list
       // Call ImageLoader constructor to initialize FileCache
       imageLoader = new ImageLoader(activity.getApplicationContext());
   }

   public int getCount() {
       return data.length;
   }

   public Object getItem(int position) {
       return position;
   }

   public long getItemId(int position) {
       return position;
   }
    
   /********* Create a holder Class to contain inflated xml file elements *********/
   public static class ViewHolder{
         
       public TextView text;
       public TextView text1;
       public TextView textWide;
       public ImageView image;
 
   }
    
   public View getView(int position, View convertView, ViewGroup parent) {
        
       View vi=convertView;
       ViewHolder holder;
         
       if(convertView==null){
             
           /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
           vi = inflater.inflate(R.layout.listview_row, null);
             
           /****** View Holder Object to contain tabitem.xml file elements ******/

           holder = new ViewHolder();
           holder.text = (TextView) vi.findViewById(R.id.text);
           holder.text1=(TextView)vi.findViewById(R.id.text1);
           holder.image=(ImageView)vi.findViewById(R.id.image);
             
          /************  Set holder with LayoutInflater ************/
           vi.setTag( holder );
       }
       else
           holder=(ViewHolder)vi.getTag();
        
        
       holder.text.setText(mStrings[position][0]);
       holder.text1.setText(mStrings[position][1]);
       ImageView image = holder.image;
        
       //DisplayImage function from ImageLoader Class
       imageLoader.DisplayImage(data[position], image);
        
       /******** Set Item Click Listner for LayoutInflater for each row ***********/
       vi.setOnClickListener(new OnItemClickListener(position));
       return vi;
   }

   private String[][] mStrings = {
		   {"10000","9000"},
		   {"1000","70"},
		   {"8000","900"},
		   {"7800","6000"},
		   {"10000","9000"},
		   {"10000","9000"},
		   {"10000","9000"},
		   {"10000","9000"},
		   {"10000","9000"},
		   {"10000","9000"},
		   {"10000","9000"}

	};
   
   @Override
   public void onClick(View arg0) {
       // TODO Auto-generated method stub
        
   }
    
    
   /********* Called when Item click in ListView ************/
   private class OnItemClickListener  implements OnClickListener{           
       private int mPosition;
        
      OnItemClickListener(int position){
            mPosition = position;
       }
        
       @Override
       public void onClick(View arg0) {
           MainActivity_ sct = (MainActivity_)activity;
           sct.onItemClick(mPosition);
       }               
   }   


}
