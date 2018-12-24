package io.openems.edge.common.type;

import java.util.Optional;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;

import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.doc.OptionsEnum;
import io.openems.edge.common.channel.value.Value;

/**
 * Handles implicit conversions between {@link OpenemsType}s
 */
public class TypeUtils {

	@SuppressWarnings("unchecked")
	public static <T> T getAsType(OpenemsType type, Object value) throws IllegalArgumentException {
		// Extract Value containers
		if (value instanceof Value<?>) {
			value = ((Value<?>) value).get();
		}
		// Extract Optionals
		if (value instanceof Optional<?>) {
			value = ((Optional<?>) value).orElse(null);
		}
		// Extract OptionsEnum
		if (value instanceof OptionsEnum) {
			value = ((OptionsEnum) value).getValue();
		}
		switch (type) {
		case BOOLEAN:
			return (T) (Boolean) value;

		case SHORT:
			if (value == null) {
				return (T) (Short) value;

			} else if (value instanceof Boolean) {
				boolean boolValue = (Boolean) value;
				return (T) Short.valueOf((boolValue ? (short) 1 : (short) 0));

			} else if (value instanceof Short) {
				return (T) (Short) value;

			} else if (value instanceof Integer) {
				int intValue = (Integer) value;
				if (intValue >= Short.MIN_VALUE && intValue <= Short.MAX_VALUE) {
					return (T) Short.valueOf((short) intValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Integer [" + value + "] is not fitting in Short range.");
				}

			} else if (value instanceof Long) {
				long longValue = (Long) value;
				if (longValue >= Short.MIN_VALUE && longValue <= Short.MAX_VALUE) {
					return (T) Short.valueOf((short) longValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Long [" + value + "] is not fitting in Short range.");
				}

			} else if (value instanceof Float) {
				float floatValue = (Float) value;
				int intValue = Math.round(floatValue);
				if (intValue >= Short.MIN_VALUE && intValue <= Short.MAX_VALUE) {
					return (T) Short.valueOf((short) intValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Float [" + value + "] is not fitting in Short range.");
				}

			} else if (value instanceof Double) {
				double doubleValue = (Double) value;
				long longValue = Math.round(doubleValue);
				if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
					return (T) Integer.valueOf((int) longValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Double [" + value + "] is not fitting in Short range.");
				}

			} else if (value instanceof String) {
				String stringValue = (String) value;
				return (T) Short.valueOf(Short.parseShort(stringValue));
			}
			break;

		case INTEGER:
			if (value == null) {
				return (T) ((Integer) value);

			} else if (value instanceof Boolean) {
				boolean boolValue = (Boolean) value;
				return (T) Integer.valueOf((boolValue ? 1 : 0));

			} else if (value instanceof Short) {
				return (T) Integer.valueOf((Short) value);

			} else if (value instanceof Integer) {
				return (T) (Integer) value;

			} else if (value instanceof Long) {
				long longValue = (Long) value;
				if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
					return (T) Integer.valueOf((int) longValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Long [" + value + "] is not fitting in Integer range.");
				}

			} else if (value instanceof Float) {
				float floatValue = (Float) value;
				return (T) (Integer) Math.round(floatValue);

			} else if (value instanceof Double) {
				double doubleValue = (Double) value;
				long longValue = Math.round(doubleValue);
				if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
					return (T) Integer.valueOf((int) longValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Double [" + value + "] is not fitting in Integer range.");
				}

			} else if (value instanceof String) {
				String stringValue = (String) value;
				return (T) Integer.valueOf(Integer.parseInt(stringValue));
			}
			break;

		case LONG:
			if (value == null) {
				return (T) (Long) value;

			} else if (value instanceof Boolean) {
				boolean boolValue = (Boolean) value;
				return (T) Long.valueOf((boolValue ? 1l : 0l));

			} else if (value instanceof Short) {
				return (T) (Long) ((Short) value).longValue();

			} else if (value instanceof Integer) {
				return (T) (Long) ((Integer) value).longValue();

			} else if (value instanceof Long) {
				return (T) (Long) value;

			} else if (value instanceof Float) {
				return (T) (Long) Math.round(Double.valueOf((Float) value));

			} else if (value instanceof Double) {
				return (T) (Long) Math.round((Double) value);

			} else if (value instanceof String) {
				String stringValue = (String) value;
				return (T) Long.valueOf(Long.parseLong(stringValue));
			}
			break;

		case FLOAT:
			if (value == null) {
				return (T) (Float) value;

			} else if (value instanceof Boolean) {
				boolean boolValue = (Boolean) value;
				return (T) Float.valueOf((boolValue ? 1f : 0f));

			} else if (value instanceof Short) {
				return (T) (Float) ((Short) value).floatValue();

			} else if (value instanceof Integer) {
				return (T) (Float) ((Integer) value).floatValue();

			} else if (value instanceof Long) {
				long longValue = (Long) value;
				if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
					return (T) (Float) Float.valueOf((int) longValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Long [" + value + "] is not fitting in Float range.");
				}

			} else if (value instanceof Float) {
				return (T) (Float) value;

			} else if (value instanceof Double) {
				double doubleValue = (Double) value;
				if (doubleValue >= Float.MIN_VALUE && doubleValue <= Float.MAX_VALUE) {
					return (T) (Float) Float.valueOf((float) doubleValue);
				} else {
					throw new IllegalArgumentException(
							"Cannot convert. Double [" + value + "] is not fitting in Integer range.");
				}

			} else if (value instanceof String) {
				String stringValue = (String) value;
				return (T) Float.valueOf(Float.parseFloat(stringValue));
			}
			break;

		case DOUBLE:
			if (value == null) {
				return (T) (Double) value;

			} else if (value instanceof Boolean) {
				boolean boolValue = (Boolean) value;
				return (T) Double.valueOf((boolValue ? 1l : 0l));

			} else if (value instanceof Short) {
				return (T) Double.valueOf((Short) value);

			} else if (value instanceof Integer) {
				return (T) Double.valueOf((Integer) value);

			} else if (value instanceof Long) {
				return (T) Double.valueOf((Long) value);

			} else if (value instanceof Float) {
				return (T) Double.valueOf((Float) value);

			} else if (value instanceof Double) {
				return (T) (Double) value;

			} else if (value instanceof String) {
				String stringValue = (String) value;
				return (T) Double.valueOf(Double.parseDouble(stringValue));
			}
			break;

		case STRING:
			if (value == null) {
				return (T) ((String) value);

			} else {
				return (T) value.toString();
			}

		}
		throw new IllegalArgumentException("Converter for value [" + value + "] of type [" + value.getClass()
				+ "] to type [" + type + "] is not implemented.");

	}

	public static JsonElement getAsJson(OpenemsType type, Object originalValue) {
		if (originalValue == null) {
			return JsonNull.INSTANCE;
		}
		Object value = TypeUtils.getAsType(type, originalValue);
		switch (type) {
		case BOOLEAN:
			return new JsonPrimitive(((Boolean) value) ? 1 : 0);
		case SHORT:
			return new JsonPrimitive((Short) value);
		case INTEGER:
			return new JsonPrimitive((Integer) value);
		case LONG:
			return new JsonPrimitive((Long) value);
		case FLOAT:
			return new JsonPrimitive((Float) value);
		case DOUBLE:
			return new JsonPrimitive((Double) value);
		case STRING:
			return new JsonPrimitive((String) value);
		}
		throw new IllegalArgumentException("Converter for value [" + value + "] to JSON is not implemented.");
	}
}
