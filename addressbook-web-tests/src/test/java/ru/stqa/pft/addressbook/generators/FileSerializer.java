package ru.stqa.pft.addressbook.generators;

import com.google.gson.*;
import java.io.File;
import java.lang.reflect.Type;;

public class FileSerializer implements JsonSerializer<File> {
  @Override
  public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject result = new JsonObject();
    if (src.isFile()) {
      result.add("photo", new JsonPrimitive(src.toString()));
    }
    return new JsonPrimitive(src.toString());
  }
}

