JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN = Main
SOURCES = $(shell find $(SRC_DIR) -name '*.java')
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

all: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JAVAC) -d $(BIN_DIR) $<

run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN)

clean:
	$(RM) -r $(BIN_DIR)

.PHONY: all run clean
