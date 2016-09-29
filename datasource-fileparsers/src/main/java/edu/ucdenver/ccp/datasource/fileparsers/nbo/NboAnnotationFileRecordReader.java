package edu.ucdenver.ccp.datasource.fileparsers.nbo;

/*
 * #%L
 * Colorado Computational Pharmacology's common module
 * %%
 * Copyright (C) 2012 - 2015 Regents of the University of Colorado
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Regents of the University of Colorado nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

import java.io.File;
import java.io.IOException;

import edu.ucdenver.ccp.common.download.HttpDownload;
import edu.ucdenver.ccp.common.file.CharacterEncoding;
import edu.ucdenver.ccp.common.file.reader.Line;
import edu.ucdenver.ccp.common.file.reader.StreamLineReader;
import edu.ucdenver.ccp.datasource.fileparsers.SingleLineFileRecordReader;
import edu.ucdenver.ccp.datasource.identifiers.mgi.MgiGeneID;
import edu.ucdenver.ccp.datasource.identifiers.ncbi.gene.EntrezGeneID;
import edu.ucdenver.ccp.datasource.identifiers.obo.HumanPhenotypeID;
import edu.ucdenver.ccp.datasource.identifiers.obo.NeurobehaviorOntologyID;

public class NboAnnotationFileRecordReader extends SingleLineFileRecordReader<NboAnnotationFileRecord> {

	@HttpDownload(url = "http://phenomebrowser.net/ontofunc/nbo/nbo-list.txt", decompress = false)
	private File nboAnnotationFile;

	public static final CharacterEncoding ENCODING = CharacterEncoding.US_ASCII;
	public static final String COMMENT_PREFIX = null;

	/**
	 * Constructor.
	 * 
	 * @param dataFile
	 * @param encoding
	 * @param skipLinePrefix
	 * @throws IOException
	 */
	public NboAnnotationFileRecordReader(File dataFile, CharacterEncoding encoding) throws IOException {
		super(dataFile, encoding, COMMENT_PREFIX);
	}

	public NboAnnotationFileRecordReader(File workDirectory, boolean clean) throws IOException {
		super(workDirectory, ENCODING, COMMENT_PREFIX, null, null, clean);
	}

	@Override
	protected StreamLineReader initializeLineReaderFromDownload(CharacterEncoding encoding, String skipLinePrefix)
			throws IOException {
		return new StreamLineReader(nboAnnotationFile, encoding, skipLinePrefix);
	}
	
	@Override
	protected NboAnnotationFileRecord parseRecordFromLine(Line line) {
		String text = line.getText();
		String[] toks = text.split("\\t", -1);

		MgiGeneID mgiId = new MgiGeneID(toks[0]);
		String geneSymbol = toks[1];
		String geneName = toks[2];
		NeurobehaviorOntologyID termId = new NeurobehaviorOntologyID(toks[3]);
		String nboTermName = toks[4];

		NboAnnotationFileRecord r = new NboAnnotationFileRecord(mgiId, geneSymbol, geneName, termId, nboTermName,
				line.getByteOffset(), line.getLineNumber());

		return r;
	}
}
