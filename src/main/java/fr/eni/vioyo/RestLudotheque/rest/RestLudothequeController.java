package fr.eni.vioyo.RestLudotheque.rest;

import fr.eni.vioyo.RestLudotheque.bll.AddressService;
import fr.eni.vioyo.RestLudotheque.bll.ClientService;
import fr.eni.vioyo.RestLudotheque.bo.Address;
import fr.eni.vioyo.RestLudotheque.bo.Client;
import fr.eni.vioyo.RestLudotheque.exceptions.BONotInDBException;
import fr.eni.vioyo.RestLudotheque.exceptions.NoIDException;
import fr.eni.vioyo.RestLudotheque.exceptions.NullBOException;
import fr.eni.vioyo.RestLudotheque.rest.dto.ClientAdrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class RestLudothequeController {

    @Autowired
    private ClientService serv;

    @Autowired
    private AddressService adrServ;

//    @GetMapping
//    public ResponseEntity<ApiResponse<List<Client>>> getAllClients(){
//        // no service function yet
//        return new ResponseEntity.ok(new ApiResponse<>(true, "not implemented", sgdsgv))
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> addClient(@RequestBody ClientAdrDTO dto){
        Client cl = dto.getClient();
        Address adr = dto.getAddress();
        System.out.println(dto);
        try {
            Client clNew = this.serv.addClient(cl, adr);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<Client>(true, "client created successfully", null, clNew));
        } catch (NullBOException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "error " + e.getMessage()));
        } catch (BONotInDBException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "error " + e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable("id") int id){
        this.serv.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "deleted client " + id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateClient(@PathVariable("id") int id, @RequestBody Client cl){
        try {
            cl.setId(id);
            cl = this.serv.updateClient(cl);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(true, "updated client ", null, cl));
        } catch (NoIDException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        } catch (NullBOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        } catch (BONotInDBException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        }
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable("id") int clientId, @RequestBody Address adr){
        try {
            Client cliDB = this.serv.findById(clientId);
            adr.setId(cliDB.getAddress().getId());
            adr = this.adrServ.updateAddress(adr);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<Address>(true, "adress updated", null, adr));
        } catch (BONotInDBException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        } catch (NullBOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        } catch (NoIDException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "wrong params" + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Client>>> getAllClientsNameStart(@RequestParam String nameQuery){
        List<Client> founds = this.serv.findClientsByNameStart(nameQuery);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<List<Client>>(true, "success", null, founds));
    }



}
