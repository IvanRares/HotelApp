package com.example.hotelapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Offers",
        foreignKeys = {
                @ForeignKey(entity = Price.class,
                        parentColumns = "PriceId",
                        childColumns = "PriceId",
                        onDelete = ForeignKey.CASCADE)
        },indices = {@Index(name = "Offers_IX_Offers_PriceId", value = "PriceId", unique = false, orders = Index.Order.ASC)})
public class Offer {
    @PrimaryKey
    private int OfferId;

    @ColumnInfo(name = "PriceId")
    private int priceId;

    @ColumnInfo(name = "OfferName")
    @NotNull
    private String OfferName;

    @ColumnInfo(name = "Active", defaultValue = "1")
    private boolean active;

    public int getOfferId() {
        return OfferId;
    }

    public void setOfferId(int offerId) {
        OfferId = offerId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @NotNull
    public String getOfferName() {
        return OfferName;
    }

    public void setOfferName(@NotNull String offerName) {
        OfferName = offerName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
