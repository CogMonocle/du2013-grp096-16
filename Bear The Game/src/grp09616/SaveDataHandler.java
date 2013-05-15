package grp09616;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveDataHandler
{
	public ArrayList<ListEntry> read(String file)
	{
		ArrayList<ListEntry> output = new ArrayList<ListEntry>();
		try
		{
			// If not a text file, throw an error.
			// Use IndexOutOfBoundsException to cover the case that there is no
			// '.' in the filename,
			// Which causes an IndexOutOfBoundsException by sending a -1 to the
			// substring() function
			if (!file.substring(file.lastIndexOf('.')).equals(".txt"))
			{
				throw new IndexOutOfBoundsException();
			}

			// Create the reader and read the file to the StringBuilder
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String type;
			String key;
			String val;
			// reads in the line. properties must be in format Type Key=Value
			while ((line = reader.readLine()) != null)
			{
				type = line.substring(0, line.indexOf(' '));
				key = line.substring(line.indexOf(' '), line.indexOf('='));
				val = line.substring(line.indexOf('='));
				output.add(new ListEntry(type, key, val));
			}
			reader.close();
		} catch (IndexOutOfBoundsException e)
		{
			// Was an error in the file type.
			e.printStackTrace();
		} catch (IOException e)
		{
			// Either could not find the document or the document had problems
			// loading.
			e.printStackTrace();
		}
		return output;
	}

	public void write(String file, ArrayList<ListEntry> input)
	{
		try
		{
			// If not a text file, throw an error.
			// Use IndexOutOfBoundsException to cover the case that there is no
			// '.' in the filename,
			// Which causes an IndexOutOfBoundsException by sending a -1 to the
			// substring() function
			if (!file.substring(file.lastIndexOf('.')).equals(".txt"))
			{
				throw new IndexOutOfBoundsException();
			}

			// Create the writer and write to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			String type;
			String key;
			String val;
			// write in the line

			for (ListEntry l : input)
			{
				type = l.getType();
				key = l.getKey();
				val = l.getValue();
				writer.write(type + " " + key + "=" + val);
				writer.newLine();
			}

			writer.close();
		} catch (IndexOutOfBoundsException e)
		{
			// Was an error in the file type.
			e.printStackTrace();
		} catch (IOException e)
		{
			// Either could not find the document or the document had problems
			// loading.
			e.printStackTrace();
		}
	}

	public class ListEntry
	{
		private final String type;
		private final String key;
		private final String value;

		public ListEntry(String t, String k, String v)
		{
			type = t;
			key = k;
			value = v;
		}

		public String getType()
		{
			return type;
		}

		public String getKey()
		{
			return key;
		}

		public String getValue()
		{
			return value;
		}
	}
}
