package com.psrapps.www.press_ldemo;

/**
 * Created by poornashekarreddy.p on 12-09-2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Eight extends Fragment {
    View myview;
    RecyclerView recyclerView;
    Adapterb adapterb;
    ArrayList<Modelb> arrayList=new ArrayList();
    String url="http://34.210.77.132:3001/psr/down/ssfiles";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.eight,container,false);
        recyclerView = (RecyclerView)myview.findViewById(R.id.recyclerbohss);
        load();
        return myview;
    }
    public void load(){
        final JsonArrayRequest getModel=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                {try
                {
                    JSONArray array = response;
                    //Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();
                    for(int x=0;x<array.length();x++)
                    {
                        JSONObject model = array.getJSONObject(x);

                        String name = model.getString("name");
                        String category = model.getString("category");
                        String path = model.getString("pdf");
                        String description = model.getString("description");
                        Modelb newmodel = new Modelb(name,description,path,category);
                        //Toast.makeText(getContext(),newmodel.getQuote().toString(),Toast.LENGTH_LONG).show();
                        arrayList.add(newmodel);

                    }
                }
                catch(JSONException e)
                {
                    Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new ItemDecorator(0,0,0,10));
                    adapterb = new Adapterb(arrayList);
                    recyclerView.setAdapter(adapterb);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(getModel);
    }
}
