package com.adrianjlane.prove05;

import android.os.Parcel;
import android.os.Parcelable;
import android.renderscript.Script;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class Scripture implements Parcelable {
    private String book;
    private int chapter;
    private int verse;

    public Scripture (String book, int chapter, int verse) {
        this.setBook(book);
        this.setChapter(chapter);
        this.setVerse(verse);
    }

    public static Scripture fromJson(String s) {
        return new Gson().fromJson(s, Scripture.class);
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getChapter() {
        return chapter;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public int getVerse() {
        return verse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getBook());
        dest.writeInt(getChapter());
        dest.writeInt(getVerse());
    }

    public static final Parcelable.Creator<Scripture> CREATOR = new Parcelable.Creator<Scripture>() {
        public Scripture createFromParcel(Parcel in) {
            return new Scripture(in);
        }

        public Scripture[] newArray(int size) {
            return new Scripture[size];
        }
    };

    private Scripture(Parcel in) {
        book = in.readString();
        chapter = in.readInt();
        verse = in.readInt();
    }

    @NonNull
    @Override
    public String toString() {
        String formattedScripture = getBook() + " " + getChapter() + ":" + getVerse();
        return formattedScripture;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
