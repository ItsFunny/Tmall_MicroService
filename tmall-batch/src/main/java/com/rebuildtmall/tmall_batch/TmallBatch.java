package com.rebuildtmall.tmall_batch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class TmallBatch
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TmallBatch.class).run(args);
	}
}
