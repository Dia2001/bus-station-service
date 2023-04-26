package com.busstation.services;

import java.io.BufferedReader;
import java.io.Writer;

public interface CsvFileService {

	void exportUsesToCsv(Writer writer);

	void importUserstoCsvFile(BufferedReader reader);

}