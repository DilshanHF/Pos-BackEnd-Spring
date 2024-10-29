package lk.ijse.aad67.posbackendspring.controllers;


import lk.ijse.aad67.posbackendspring.dto.impl.ItemDTO;
import lk.ijse.aad67.posbackendspring.exceptions.DataPersistException;
import lk.ijse.aad67.posbackendspring.exceptions.ItemNotFoundException;
import lk.ijse.aad67.posbackendspring.service.ItemService;
import lk.ijse.aad67.posbackendspring.utill.Regex;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@CrossOrigin
public class ItemController {
    @Autowired
    private  ItemService itemService;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/nextId")
    public String nextItemId(){
        return itemService.generateItemId();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO){
        try {
            itemService.saveItem(itemDTO);
            return new  ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItem();
    }
    @PutMapping(value = "/{propertyId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("propertyId") String propertyId){
        boolean isItemIdValid = propertyId.matches(Regex.ITEM_ID_REGEX);
        try {
            if (isItemIdValid) {
                itemService.updateItem(propertyId,itemDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.error("Faild with item id : ",propertyId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (ItemNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{propertyId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("propertyId") String propertyId){
        boolean isItemIdValid = propertyId.matches(Regex.ITEM_ID_REGEX);
        try {
            if (isItemIdValid) {
                itemService.deleteItem(propertyId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.error("Faild with item id : ",propertyId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (ItemNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
