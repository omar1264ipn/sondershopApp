package com.sondershop.sondershopapp.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sondershop.sondershopapp.models.ClientM;


import java.util.HashMap;
import java.util.Map;

public class ClientProvider {
    DatabaseReference mDatabase;

    public ClientProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }

    public Task<Void> create(ClientM client) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", client.getName());
        map.put("email", client.getEmail());
        map.put("fotourl", client.getFotourl());
        map.put("pais", client.getPaisM());
        map.put("puntosTotales", client.getPuntosTotalesM());
        map.put("gkeR", client.getGkeR());
        map.put("idgruR", client.getIdgruR());
        map.put("accessCirugia", client.getAccessCirugia());
        map.put("accessMedGeneral", client.getAccessMedGeneral());
        map.put("accessUrgencias", client.getAccessUrgencias());
        map.put("accessOftalmolo", client.getAccessOftalmolo());
        map.put("accessEndocrino", client.getAccessEndocrino());
        map.put("accessNeurologia", client.getAccessNeurologia());
        map.put("accessTraumatologia", client.getAccessOftalmolo());
        map.put("accessDermatologia", client.getAccessEndocrino());
        map.put("accessOtorrinola", client.getAccessNeurologia());
        map.put("accessEpidemiologia", client.getAccessOftalmolo());
        map.put("accessExamen", client.getAccessEndocrino());
        return mDatabase.child(client.getId()).setValue(map);
    }
}
