import java.io.{SequenceInputStream, FileInputStream}
import collection.JavaConversions._

import org.antlr.v4.runtime._
import org.scalatest.FunSuite

import scala.io.Source

/**
 * Created by hyokota on 2015/05/14.
 */
class CompleteMatchTestSuite extends FunSuite {

  def getFilePath(filename: String): String = getClass.getResource(filename).getPath
  def getExpectedString(filename: String): String = Source.fromFile(getFilePath(filename)).mkString

  def getResult(filenames :Array[String]): String = {
    val files = filenames.map(getFilePath)

    val fileStreams = files.map(new FileInputStream(_))
    val stream = new SequenceInputStream(fileStreams.toIterator)
    val input = new ANTLRInputStream(stream)

    val lexer = new ObjCLexer(input)
    val tokens = new CommonTokenStream(lexer)
    val parser = new ObjCParser(tokens)

    val root = parser.translation_unit
    val converter = new ObjC2SwiftConverter(root)
    converter.getResult + "\n"
  }

  test("sample complete match test") {
    val expected = getExpectedString("/sample.swift")
    val actual = getResult(Array("/sample.h", "/sample.m"))

    assert(expected == actual)
  }

  test("class_method_sample complete match test") {
    val expected = getExpectedString("/class_method_sample.swift")
    val actual = getResult(Array("/class_method_sample.h", "/class_method_sample.m"))

    assert(expected == actual)
  }


  test("control_flow_sample complete match test") {
    val expected = getExpectedString("/control_flow_sample.swift")
    val actual = getResult(Array("/control_flow_sample.h", "/control_flow_sample.m"))

    assert(expected == actual)
  }

  test("declaration_sample complete match test") {
    val expected = getExpectedString("/declaration_sample.swift")
    val actual = getResult(Array("/declaration_sample.h", "/declaration_sample.m"))

    assert(expected == actual)
  }

  test("enum_sample complete match test") {
    val expected = getExpectedString("/enum_sample.swift")
    val actual = getResult(Array("/enum_sample.h"))

    assert(expected == actual)
  }

  test("expression_sample complete match test") {
    val expected = getExpectedString("/expression_sample.swift")
    val actual = getResult(Array("/expression_sample.h", "/expression_sample.m"))

    assert(expected == actual)
  }

  test("instance_method_sample complete match test") {
    val expected = getExpectedString("/instance_method_sample.swift")
    val actual = getResult(Array("/instance_method_sample.h", "/instance_method_sample.m"))

    assert(expected == actual)
  }

  test("property_sample complete match test") {
    val expected = getExpectedString("/property_sample.swift")
    val actual = getResult(Array("/property_sample.h"))

    assert(expected == actual)
  }

  test("protocol_sample complete match test") {
    val expected = getExpectedString("/protocol_sample.swift")
    val actual = getResult(Array("/protocol_sample.h", "/protocol_sample.m"))

    assert(expected == actual)
  }

  test("string_with_format_sample complete match test") {
    val expected = getExpectedString("/string_with_format_sample.swift")
    val actual = getResult(Array("/string_with_format_sample.h", "/string_with_format_sample.m"))

    assert(expected == actual)
  }
}
