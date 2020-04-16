package model;

import javax.swing.text.Document;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;

public class StudentCodec implements CollectibleCodec<Student>{

	private final CodecRegistry registry;
	private final Codec<Document> documentCodec;

	/**
	 * Default constructor.
	 */
	public StudentCodec() {
		this.registry = MongoClient.getDefaultCodecRegistry();
		this.documentCodec = this.registry.get(Document.class);
	}

	/**
	 * Codec constructor.
	 * @param codec The existing codec to use.
	 */
	public StudentCodec(Codec<Document> codec) {
		this.documentCodec = codec;
		this.registry = MongoClient.getDefaultCodecRegistry();
	}

	/**
	 * Registry constructor.
	 * @param registry The CodecRegistry to use.
	 */
	public StudentCodec(CodecRegistry registry) {
		this.registry = registry;
		this.documentCodec = this.registry.get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Student value, EncoderContext encoderContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<Student> getEncoderClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student decode(BsonReader reader, DecoderContext decoderContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student generateIdIfAbsentFromDocument(Student document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean documentHasId(Student document) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BsonValue getDocumentId(Student document) {
		// TODO Auto-generated method stub
		return null;
	}
}