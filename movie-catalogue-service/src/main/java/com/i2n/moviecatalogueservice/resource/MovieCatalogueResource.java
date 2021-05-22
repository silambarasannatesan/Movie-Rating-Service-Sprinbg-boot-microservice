package com.i2n.moviecatalogueservice.resource;

import com.i2n.moviecatalogueservice.models.CatalogueItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

    @GetMapping("/{userid}")
    public List<CatalogueItem> getCatalogueList(@PathVariable("userid") String userId) {
        return Collections.singletonList(new CatalogueItem("Shawsahnk Redemption", "Test", 4)
        );

    }
}
