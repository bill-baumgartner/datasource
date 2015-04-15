package edu.ucdenver.ccp.fileparsers.ebi.uniprot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import edu.ucdenver.ccp.common.download.FtpDownload;
import edu.ucdenver.ccp.common.file.CharacterEncoding;
import edu.ucdenver.ccp.common.ftp.FTPUtil.FileType;
import edu.ucdenver.ccp.datasource.identifiers.ncbi.taxonomy.NcbiTaxonomyID;
import edu.ucdenver.ccp.fileparsers.download.FtpHost;

public class TremblXmlFileRecordReader extends UniProtXmlFileRecordReader {

	public static final String FTP_FILE_NAME = "uniprot_trembl.xml.gz";
	public static final CharacterEncoding ENCODING = CharacterEncoding.US_ASCII;

	@FtpDownload(server = FtpHost.UNIPROT_KNOWLEDGEBASE_HOST, path = FtpHost.UNIPROT_KNOWLEDGEBASE_PATH, filename = FTP_FILE_NAME, filetype = FileType.BINARY)
	private File tremblKbFile;

//	public TremblXmlFileRecordReader(File workDirectory, boolean clean) throws IOException {
//		super(workDirectory, clean);
//	}
//
//	public TremblXmlFileRecordReader(File uniprotXmlFile) throws IOException {
//		super(uniprotXmlFile);
//	}

	public TremblXmlFileRecordReader(File workDirectory, boolean clean, Set<NcbiTaxonomyID> taxonIds)
			throws IOException {
		super(workDirectory, clean, taxonIds);
	}

	public TremblXmlFileRecordReader(File uniprotXmlFile, Set<NcbiTaxonomyID> taxonIds) throws IOException {
		super(uniprotXmlFile, taxonIds);
	}

	@Override
	protected InputStream initializeInputStreamFromDownload() throws IOException {
		return new GZIPInputStream(new FileInputStream(tremblKbFile));
	}

}