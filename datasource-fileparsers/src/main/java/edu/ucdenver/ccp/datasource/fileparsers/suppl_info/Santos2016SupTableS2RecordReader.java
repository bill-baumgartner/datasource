package edu.ucdenver.ccp.datasource.fileparsers.suppl_info;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import edu.ucdenver.ccp.common.download.HttpDownload;
import edu.ucdenver.ccp.common.file.CharacterEncoding;
import edu.ucdenver.ccp.common.file.reader.Line;
import edu.ucdenver.ccp.datasource.fileparsers.ExcelFileRecordReader;

public class Santos2016SupTableS2RecordReader extends ExcelFileRecordReader<Santos2016SupTableS2FileData> {

	// http://www.nature.com/nrd/journal/vaop/ncurrent/extref/nrd.2016.230-s2.xlsx
	@HttpDownload(url = "http://www.nature.com/nrd/journal/vaop/ncurrent/extref/nrd.2016.230-s2.xlsx", fileName = "nrd.2016.230-s2.xlsx")
	private File xlsxFile;

	public Santos2016SupTableS2RecordReader(File dataFile, CharacterEncoding encoding) throws IOException {
		super(dataFile, encoding, null);
	}

	public Santos2016SupTableS2RecordReader(File workDirectory, CharacterEncoding encoding, boolean clean)
			throws IOException {
		super(workDirectory, encoding, null, null, null, clean);
	}

	public Santos2016SupTableS2RecordReader(InputStream stream, CharacterEncoding encoding) throws IOException {
		super(stream, encoding, null);
	}

	@Override
	protected Santos2016SupTableS2FileData parseRecordFromLine(Line line) {
		return Santos2016SupTableS2FileData.parseSupTableS2Line(line);
	}

	public static void main(String[] args) {
		try {
			for (Santos2016SupTableS2RecordReader rr = new Santos2016SupTableS2RecordReader(new File(
					"/Users/bill/Downloads/nrd.2016.230-s2.xlsx"), CharacterEncoding.ISO_8859_1); rr.hasNext();) {
				Santos2016SupTableS2FileData record = rr.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
