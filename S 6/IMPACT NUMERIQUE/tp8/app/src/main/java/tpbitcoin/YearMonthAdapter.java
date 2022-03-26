package tpbitcoin;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.YearMonth;

public class YearMonthAdapter extends TypeAdapter<YearMonth> {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param writer
     * @param yearMonth the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter writer, YearMonth yearMonth) throws IOException {
        if (yearMonth == null) {
            writer.nullValue();
            return;
        }
        String ym = yearMonth.getMonth().getValue() + "." + yearMonth.getYear();
        writer.value(ym);
    }



    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param reader
     * @return the converted Java object. May be null.
     */
    @Override
    public YearMonth read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        String ym = reader.nextString();
        String[] parts = ym.split("\\.");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        return YearMonth.of(year,month);
    }
}
